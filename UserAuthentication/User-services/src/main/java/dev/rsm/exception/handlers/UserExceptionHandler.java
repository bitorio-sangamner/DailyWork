package dev.rsm.exception.handlers;

import dev.rsm.dtos.response.ApiErrorResponse;
import dev.rsm.exception.EmailException;
import dev.rsm.exception.LoginException;
import dev.rsm.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<?> handleNoUserLoginCredentialsException(final LoginException exception) {
        log.error(String.format("Error message: %s", exception.getMessage()), exception);
        var response = new ApiErrorResponse(exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUsernameAlreadyExistException(final UserException exception) {
        log.error(String.format("Error message: %s", exception.getMessage()), exception);
        var response = new ApiErrorResponse(exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<?> handleNoEmailExistException(final UserException exception) {
        log.error(String.format("Error message: %s", exception.getMessage()), exception);
        var response = new ApiErrorResponse(exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownException(final Exception exception) {
        log.error(
                String.format("Error message: %s", exception.getMessage()),
                exception
        );
        var response = new ApiErrorResponse(
                500,
                "Internal server error",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}