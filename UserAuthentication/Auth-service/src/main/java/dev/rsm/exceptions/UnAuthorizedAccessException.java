package dev.rsm.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnAuthorizedAccessException extends RuntimeException {
    private final String message;
    private final int errorCode;

    public UnAuthorizedAccessException(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}