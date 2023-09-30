package dev.rsm.dtos;

public record UserPasswordResetRequest(String email, String password, String resetPasswordToken) {
}
