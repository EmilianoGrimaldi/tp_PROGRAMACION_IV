package com.trabajopractico.fundamentosdespring.categoria;

import com.trabajopractico.fundamentosdespring.models.Categoria;

public record CategoriaEdit(
        String nombre,
        String descripcion
) {
    public void applyTo(Categoria categoria){
        if (this.nombre != null){
            categoria.setNombre(this.nombre);
        }
        if (this.descripcion != null){
            categoria.setDescripcion(this.descripcion);
        }
    }
}
