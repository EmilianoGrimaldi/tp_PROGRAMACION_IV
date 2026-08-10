package com.trabajopractico.fundamentosdespring.categoria;

import com.trabajopractico.fundamentosdespring.models.Categoria;

public record CategoriaDto (
        Long id,
        String nombre,
        // ERROR: El campo se llama 'description' (en inglés) pero en la entidad Categoria
        // el campo correspondiente se llama 'descripcion' (en español).
        // Esto genera inconsistencia de nomenclatura con el resto del proyecto.
        String descripcion
){
    public static CategoriaDto toDto(Categoria categoria){
        return new CategoriaDto(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
    }
}

