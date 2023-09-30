package dev.rsm.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UserException extends RuntimeException{

    private final String message;
    private final int errorCode;
}
