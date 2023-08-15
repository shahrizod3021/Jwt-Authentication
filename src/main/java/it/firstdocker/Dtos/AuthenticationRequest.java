package it.firstdocker.Dtos;

public record AuthenticationRequest(
        String username,
        String password
) {
}
