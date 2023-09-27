package dev.rsm.exceptions.handlers;

import dev.rsm.dtos.ApiErrorResponse;
import dev.rsm.exceptions.UsernameAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistException.class)
    public ResponseEntity<?> handleApplicationException(final UsernameAlreadyExistException exception) {
        log.error(String.format("Error message: %s", exception.getMessage()), exception);
        var response = new ApiErrorResponse(exception.getErrorCode(),
                exception.getMessage(),
                400,
                HttpStatusCode.valueOf(400).toString(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownException(final Exception exception) {
        var guid = UUID.randomUUID().toString();
        log.error(
                String.format("Error GUID=%s; error message: %s", guid, exception.getMessage()),
                exception
        );
        var response = new ApiErrorResponse(
                "500",
                "Internal server error",
                404,
                HttpStatusCode.valueOf(404).toString(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}