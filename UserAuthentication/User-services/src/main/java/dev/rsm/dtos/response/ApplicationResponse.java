package dev.rsm.dtos.response;

import java.time.LocalDateTime;

public record ApplicationResponse(String message, LocalDateTime localDateTime) {
}
