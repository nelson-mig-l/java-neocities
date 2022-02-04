package org.neocities.api;

import java.io.File;

public interface NeoCitiesApi {

    BaseResponse upload(File file);

    BaseResponse delete(String filename);

    ListResponse list();

    InfoResponse info();

    KeyResponse key();
}
