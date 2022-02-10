package org.neocities.api;

import java.io.File;

public interface NeoCitiesApi {

    BaseResponse upload(File file, String targetName);

    BaseResponse upload(FileToUpload... filesToUpload);

    BaseResponse delete(String... fileNames);

    ListResponse list();

    InfoResponse info();

    InfoResponse info(String siteName);

    KeyResponse key();
}
