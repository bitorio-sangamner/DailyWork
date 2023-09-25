package dev.rsm.dtos;

public record LoginCredentialsWithToken(String email, String password, String resetPasswordToken) {
}
