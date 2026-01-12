package org.neocities.api;

import java.util.Optional;

public class NeoCitiesApiError extends RuntimeException {

    /**
     * The actual error response from the neocities API.
     * Will be null if this exception was not built from a response.
     */
    public final BaseResponse error;

    public static NeoCitiesApiError fromResponse(final BaseResponse instance) {
        final String message = new StringBuilder("API returned unexpected result: '")
                .append(instance.getResult())
                .append("' (ErrorType=")
                .append(instance.getErrorType())
                .append(", message=")
                .append(instance.getMessage())
                .append(").")
                .toString();
        return new NeoCitiesApiError(message, instance);
    }

    private NeoCitiesApiError(final String message, final BaseResponse instance){
        super(message);
        error = instance;
    }

    public NeoCitiesApiError(final String message) {
        this(message, null);
    }

    public NeoCitiesApiError(final Throwable cause) {
        super(cause);
        error = null;
    }

    /**
     * Safely provides access to the error response from Neocities (if present)
     * @return the value of {@link NeoCitiesApiError#error} wrapped in an {@link Optional}
     */
    public Optional<BaseResponse> getError() {
        return Optional.ofNullable(error);
    }
}
