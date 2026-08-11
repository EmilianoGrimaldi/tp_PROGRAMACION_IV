package com.trabajopractico.fundamentosdespring.categoria;

import com.trabajopractico.fundamentosdespring.models.Categoria;

public record CategoriaDto (
        Long id,
        String nombre,
        String descripcion
){
    public static CategoriaDto toDto(Categoria categoria){
        return new CategoriaDto(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
    }
}

