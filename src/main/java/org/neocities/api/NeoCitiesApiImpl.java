package org.neocities.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class NeoCitiesApiImpl implements NeoCitiesApi {

    public static final String DEFAULT_ADDRESS = "https://neocities.org/api";
    public static final String RFC2822 = "EEE, dd MMM yyyy HH:mm:ss Z";

    private static final HttpUrl DEFAULT_URL = HttpUrl.parse(DEFAULT_ADDRESS);

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new GsonBuilder()
            .setDateFormat(RFC2822)
            .create();

    private final String credential;

    public NeoCitiesApiImpl(final String user, final String password) {
        this.credential = Credentials.basic(user, password);
    }

    public NeoCitiesApiImpl(final String apiKey) {
        this.credential = "Bearer " + apiKey;
    }

    @Override
    public BaseResponse upload(final File file) {
        return upload(file, file.getName());
    }

    @Override
    public BaseResponse upload(final File file, final String targetName) {
        return upload(new FileToUpload(file.getPath(), targetName));
    }

    @Override
    public BaseResponse upload(final FileToUpload... filesToUpload) {
        final HttpUrl url = urlForPath("upload")
                .build();
        final MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        Arrays.stream(filesToUpload).forEach(fileToUpload -> {
            final String sourcePath = fileToUpload.getSourcePath();
            final String targetPath = fileToUpload.getTargetPath();
            final RequestBody body = requestBodyFromFile(sourcePath);
            builder.addFormDataPart(targetPath, Path.of(sourcePath).getFileName().toString(), body);
        });
        final RequestBody requestBody = builder
                .build();
        final Request request = authorizedRequest(url)
                .post(requestBody)
                .build();
        return ask(request, BaseResponse.class);
    }

    private RequestBody requestBodyFromFile(final String path) {
        try {
            return RequestBody.create(Files.readAllBytes(Path.of(path)));
        } catch (IOException e) {
            throw new NeoCitiesApiError(e);
        }
    }

    @Override
    public BaseResponse delete(final String... fileNames) {
        final HttpUrl url = urlForPath("delete")
                .build();
        final MultipartBody.Builder builder = new MultipartBody.Builder();
        Arrays.stream(fileNames).forEach(fileName -> builder.addFormDataPart("filenames[]", fileName));
        final RequestBody requestBody = builder.build();
        final Request request = authorizedRequest(url)
                .post(requestBody)
                .build();
        return ask(request, BaseResponse.class);
    }

    @Override
    public ListResponse list() {
        final HttpUrl url = urlForPath("list")
                .build();
        final Request request = authorizedRequest(url)
                .build();
        return ask(request, ListResponse.class);
    }

    @Override
    public InfoResponse info() {
        final HttpUrl url = urlForPath("info")
                .build();
        final Request request = authorizedRequest(url)
                .build();
        return ask(request, InfoResponse.class);
    }

    @Override
    public InfoResponse info(final String siteName) {
        final HttpUrl url = urlForPath("info")
                .addQueryParameter("sitename", siteName)
                .build();
        final Request request = authorizedRequest(url)
                .build();
        return ask(request, InfoResponse.class);
    }

    @Override
    public KeyResponse key() {
        final HttpUrl url = urlForPath("key")
                .build();
        final Request request = authorizedRequest(url)
                .build();
        return ask(request, KeyResponse.class);
    }

    private HttpUrl.Builder urlForPath(final String path) {
        return DEFAULT_URL.newBuilder().addPathSegment(path);
    }

    private Request.Builder authorizedRequest(final HttpUrl url) {
        return new Request.Builder()
                .addHeader("Authorization", credential)
                .url(url);
    }

    private <T extends BaseResponse> T ask(final Request request, final Class<T> classOf) {
        try (final Response response = client.newCall(request).execute()) {
            final T instance = gson.fromJson(response.body().string(), classOf);
            check(instance);
            return instance;
        } catch (IOException e) {
            throw new NeoCitiesApiError(e);
        }
    }

    private void check(final BaseResponse instance) {
        if (!BaseResponse.SUCCESS.equals(instance.getResult())) {
            throw NeoCitiesApiError.fromResponse(instance);
        }
    }

}
