package dev.rsm.dto;

public record ResetEmailRequest(String token, String recipientEmail) {
}
