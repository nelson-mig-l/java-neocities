package org.neocities;

import org.neocities.api.*;

import java.io.File;

public class Example {

    public static void main(String[] args) {

        final NeoCitiesApi api = getApi(args);

        final InfoResponse myInfo = api.info();
        System.out.println(myInfo.getInfo().getSiteName());
        System.out.println(myInfo.getInfo().getHits());

        final InfoResponse otherInfo = api.info("edz");
        System.out.println(otherInfo.getInfo().getSiteName());
        System.out.println(otherInfo.getInfo().getHits());

        final KeyResponse key = api.key();
        System.out.println(key.getApiKey());

        final ListResponse list = api.list();
        list.getFiles().forEach(f -> System.out.println(f.getPath()));

        //api.delete("about.html");
        //api.upload(new File("index.html"));
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
