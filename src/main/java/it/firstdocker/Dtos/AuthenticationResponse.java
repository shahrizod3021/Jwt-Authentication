package it.firstdocker.Dtos;

public record AuthenticationResponse(
        String token,
        CustomerDto customerDto

) {

}
