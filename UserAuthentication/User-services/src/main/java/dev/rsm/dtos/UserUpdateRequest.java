package dev.rsm.dtos;

public record UserUpdateRequest(int id, String firstName, String lastName, String username, String password, String email) {
}
