package org.neocities.api;

public class BaseResponse {

    private String result;
    private String error_type;
    private String message;

    public String getResult() {
        return result;
    }

    public String getErrorType() {
        return error_type;
    }

    public String getMessage() {
        return message;
    }
}
