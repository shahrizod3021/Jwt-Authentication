package it.firstdocker.Dtos;

public record ApiResponse<T>(
        String message,
        boolean success,

        Integer status
) {
}
