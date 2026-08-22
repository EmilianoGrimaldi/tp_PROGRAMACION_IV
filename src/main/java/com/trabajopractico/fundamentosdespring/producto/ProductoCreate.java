package com.trabajopractico.fundamentosdespring.producto;

import com.trabajopractico.fundamentosdespring.models.Categoria;
import com.trabajopractico.fundamentosdespring.models.Producto;
import jakarta.validation.constraints.*;

public record ProductoCreate(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String nombre,

        @Positive(message = "El precio debe ser mayor que 0")
        double precio,

        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcion,

        @Min(value = 0, message = "El stock no puede ser negativo")
        int stock,

        @Pattern(regexp = "^(http|https)://.*$", message = "La imagen debe ser una URL válida")
        String imagen,

        boolean disponible,

        @NotNull(message = "El ID de categoría es obligatorio")
        Long categoriaId
) {
    public Producto toEntity(Categoria categoria){
        return new Producto(this.nombre,this.precio,this.descripcion,this.stock,this.imagen, this.disponible, categoriaId);
    }
}
