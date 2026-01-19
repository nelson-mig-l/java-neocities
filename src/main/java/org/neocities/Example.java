package org.neocities;

import org.neocities.api.*;
import org.neocities.utils.FileToUploadHelper;

public class Example {

    /**
     * Example usage.
     * @param args should be your username and password or API key.
     */
    public static void main(final String[] args) {

        final NeoCitiesApi api = getApi(args);

        final InfoResponse myInfo = api.info();


        title("Showing hits and views for your site");
        print(myInfo.getInfo().getSiteName());
        print(myInfo.getInfo().getHits());
        title("Showing creation and last update dates for your site");
        print(myInfo.getInfo().getCreatedAt());
        print(myInfo.getInfo().getLastUpdate());

        final InfoResponse otherInfo = api.info("edz");
        title("Showing hits for other site");
        print(otherInfo.getInfo().getSiteName());
        print(otherInfo.getInfo().getHits());
        title("Showing creation and last update dates for other site");
        print(otherInfo.getInfo().getCreatedAt());
        print(otherInfo.getInfo().getLastUpdate());

        try {
            api.info("non-existing-site");
        } catch (NeoCitiesApiError error) {
            title("Showing details about api error");
            print(error.getErrorType());
            print(error.getErrorMessage());
        }

        final KeyResponse key = api.key();
        title("Your API key is:");
        print(key.getApiKey());

        title("Listing files");
        api.list().getFiles().forEach(f -> {
            print(f.getPath());
            print("\tdirectory:  " + f.isDirectory());
            print("\tsize:       " + f.getSize());
            print("\tsha1:       " + f.getSha1Hash());
            print("\tupdated at: " + f.getUpdatedAt());
        });

        api.upload(
                FileToUploadHelper.uploadFileToSpecificPath("sample.html", "test/hello.html"),
                FileToUploadHelper.uploadFileToDirectory("hello.txt", "test"),
                FileToUploadHelper.uploadFileToRoot("test.html")
        );

        title("Listing files after upload");
        api.list().getFiles().forEach(f -> print(f.getPath()));

        api.delete("test/hello.html", "test/hello.txt", "test", "test.html");

        title("Listing files after delete");
        api.list().getFiles().forEach(f -> print(f.getPath()));
    }

    private static void title(final Object obj) {
        print("=== " + obj + " ===");
    }

    private static void print(final Object obj) {
        System.out.println(obj.toString());
    }

    private static NeoCitiesApi getApi(final String[] args) {
        switch (args.length) {
            case 1:
                return new NeoCitiesApiImpl(args[0]);
            case 2:
                return new NeoCitiesApiImpl(args[0], args[1]);
            default:
                throw new RuntimeException("Check your arguments");
        }
    }
}
