package dev.rsm.dtos;

import java.time.LocalDateTime;

public record ApiErrorResponse(String errorCode, String message, Integer httpStatusCode, String httpStatusName, LocalDateTime localDateTime) {

}
