package it.firstdocker.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicateDataException extends RuntimeException {

    public DuplicateDataException(String message) {
        super(message);
    }

}
