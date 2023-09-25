package dev.rsm.dtos;

public record UserCredentialsSaveRequest(String username, String password, String email, String firstName, String lastName) {
}
