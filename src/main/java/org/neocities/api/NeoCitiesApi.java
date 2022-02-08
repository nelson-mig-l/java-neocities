package org.neocities.api;

import java.io.File;

public interface NeoCitiesApi {

    BaseResponse upload(File file);

    BaseResponse upload(File file, String targetName);

    BaseResponse delete(String filename);

    ListResponse list();

    InfoResponse info();

    InfoResponse info(String siteName);

    KeyResponse key();
}
