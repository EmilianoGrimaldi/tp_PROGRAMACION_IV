package com.trabajopractico.fundamentosdespring.categoria;

import com.trabajopractico.fundamentosdespring.models.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaCreate(
        @NotBlank(message = "El nombre de la categoría es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String nombre,

        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcion
) {
    public Categoria toEntity() {
        return new Categoria(this.nombre, this.descripcion);
    }
}
