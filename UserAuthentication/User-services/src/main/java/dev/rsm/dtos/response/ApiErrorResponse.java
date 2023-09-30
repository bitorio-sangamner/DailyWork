package dev.rsm.dtos.response;

import java.time.LocalDateTime;

public record ApiErrorResponse(int errorCode, String message, LocalDateTime localDateTime) {

}
