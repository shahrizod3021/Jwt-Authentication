package it.firstdocker.Dtos;


public record CustomerRegistrationRequest(String name, String email, String password, Integer age, Integer gender) {
}
