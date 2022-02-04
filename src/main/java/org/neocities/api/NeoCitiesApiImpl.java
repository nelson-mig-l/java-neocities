package org.neocities.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class NeoCitiesApiImpl implements NeoCitiesApi {

    public static final String DEFAULT_ADDRESS = "https://neocities.org/api";

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new GsonBuilder()
            .setDateFormat("EEE, dd MMM yyyy HH:mm:ss Z")
            .create();

    private final String credential;

    public NeoCitiesApiImpl(String user, String password) {
        this.credential = Credentials.basic(user, password);
    }

    public NeoCitiesApiImpl(String apiKey) {
        this.credential = "Bearer " + apiKey;
    }

    @Override
    public BaseResponse upload(final File file) {
        final RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(file.getName(), file.getName(), RequestBody.create(file, MediaType.parse("text/plain")))
                .build();
        final Request request = newBuilder("upload")
                .post(requestBody)
                .build();
        return ask(request, BaseResponse.class);
    }

    @Override
    public BaseResponse delete(final String filename) {
        final RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("filenames[]", filename)
                .build();
        final Request request = newBuilder("delete")
                .post(requestBody)
                .build();
        return ask(request, BaseResponse.class);
    }

    @Override
    public ListResponse list() {
        final Request request = newBuilder("list").build();
        return ask(request, ListResponse.class);
    }

    @Override
    public InfoResponse info() {
        final Request request = newBuilder("info")
                //.addHeader("siteName", siteName)
                .build();
        return ask(request, InfoResponse.class);
    }

    @Override
    public KeyResponse key() {
        final Request request = newBuilder("key").build();
        return ask(request, KeyResponse.class);
    }

    private Request.Builder newBuilder(final String path) {
        return new Request.Builder()
                .url(DEFAULT_ADDRESS + '/' + path)
                .addHeader("Authorization", credential);
    }

    private <T extends BaseResponse> T ask(final Request request, final Class<T> classOf) {
        try (Response response = client.newCall(request).execute()) {
            final T instance = gson.fromJson(response.body().string(), classOf);
            check(instance);
            return instance;
        } catch (IOException e) {
            throw new NeoCitiesApiError(e);
        }
    }

    private void check(final BaseResponse instance) {
        if (!instance.getResult().equals("success")) {
            throw NeoCitiesApiError.fromResponse(instance);
        }
    }

}
