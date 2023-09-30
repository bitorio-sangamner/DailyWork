package dev.rsm.dtos;

import java.time.LocalDateTime;

public record ApplicationResponse(String message, LocalDateTime localDateTime) {
}
