package dev.rsm.dtos;

import java.time.LocalDateTime;

public record ApiErrorResponse(int errorCode, String message, Integer httpStatusCode, String httpStatusName, LocalDateTime localDateTime) {

}
