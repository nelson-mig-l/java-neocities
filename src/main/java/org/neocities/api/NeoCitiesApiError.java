package org.neocities.api;

public class NeoCitiesApiError extends RuntimeException {

    public static NeoCitiesApiError fromResponse(final BaseResponse instance) {
        final String message = new StringBuilder("API returned unexpected result: '")
                .append(instance.getResult())
                .append("' (ErrorType=")
                .append(instance.getErrorType())
                .append(", message")
                .append(instance.getMessage())
                .append(").")
                .toString();
        return new NeoCitiesApiError(message);
    }

    public NeoCitiesApiError(final String message) {
        super(message);
    }

    public NeoCitiesApiError(final Throwable cause) {
        super(cause);
    }
}
