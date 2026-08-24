package com.trabajopractico.fundamentosdespring.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Maneja errores de validación (Bean Validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> detalles = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorDTO errorDTO = ErrorDTO.of(HttpStatus.BAD_REQUEST.value(),
                "Error de validación", detalles);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    // Maneja errores de ConstraintViolation (en otros contextos)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> detalles = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .toList();

        ErrorDTO errorDTO = ErrorDTO.of(HttpStatus.BAD_REQUEST.value(),
                "Error de validación", detalles);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    // Maneja ResourceNotFoundException — recurso no encontrado (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorDTO errorDTO = ErrorDTO.simple(HttpStatus.NOT_FOUND.value(),
                "Recurso no encontrado", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    // Maneja IllegalArgumentException (por ejemplo en validaciones manuales)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorDTO errorDTO = ErrorDTO.simple(HttpStatus.BAD_REQUEST.value(),
                "Argumento inválido", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    // Maneja cualquier otro error no controlado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGeneric(Exception ex) {
        ErrorDTO errorDTO = ErrorDTO.simple(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}
