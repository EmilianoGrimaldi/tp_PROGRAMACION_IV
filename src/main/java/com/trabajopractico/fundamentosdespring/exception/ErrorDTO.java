package com.trabajopractico.fundamentosdespring.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorDTO(
        LocalDateTime timestamp,
        int status,
        String mensaje,
        String detalles
) {
    public static ErrorDTO of(int status, String error, List<String> detalles) {
        return new ErrorDTO(LocalDateTime.now(), status, error, detalles.toString());
    }

    public static ErrorDTO simple(int status, String error, String detalle) {
        return new ErrorDTO(LocalDateTime.now(), status, error, List.of(detalle).toString());
    }
}
