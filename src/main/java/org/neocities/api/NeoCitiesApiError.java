package org.neocities.api;

public class NeoCitiesApiError extends RuntimeException {

    private final String errorType;
    private final String errorMessage;

    public static NeoCitiesApiError fromResponse(final BaseResponse instance) {
        return new NeoCitiesApiError(instance.getErrorType(), instance.getMessage());
    }

    private static String createMessage(final String errorType, final String errorMessage) {
        return new StringBuilder("API returned an error: ")
                .append("(ErrorType=")
                .append(errorType)
                .append(", message=")
                .append(errorMessage)
                .append(").")
                .toString();
    }

    private NeoCitiesApiError(final String errorType, final String errorMessage) {
        super(createMessage(errorType, errorMessage));
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
