package it.firstdocker.Exceptions;

import java.time.LocalDateTime;
public record ApiError(
        String path,
        String message,
        int statusCode,
        LocalDateTime localeDateTime
        ) {
}
