package dev.rsm.dtos;

public record UserRegistrationRequest(String firstName, String lastName, String username, String password, String email) {
}
