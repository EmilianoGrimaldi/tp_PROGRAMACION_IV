package com.trabajopractico.fundamentosdespring.categoria;

import com.trabajopractico.fundamentosdespring.models.Categoria;

public record CategoriaCreate (
        String nombre,
        String descripcion
){
    public Categoria toEntity(){
        return new Categoria(this.nombre, this.descripcion);
    }
}
