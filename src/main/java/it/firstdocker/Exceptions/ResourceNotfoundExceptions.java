package it.firstdocker.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotfoundExceptions extends RuntimeException {

    public ResourceNotfoundExceptions(String message) {
        super(message);
    }
}
