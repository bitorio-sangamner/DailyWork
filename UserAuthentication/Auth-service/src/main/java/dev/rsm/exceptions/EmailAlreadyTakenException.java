

package dev.rsm.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class EmailAlreadyTakenException extends RuntimeException{
    private final String message;
    private final HttpStatusCode httpStatusCode;
    private final String errorCode;

}