package com.trabajopractico.fundamentosdespring.producto;

import com.trabajopractico.fundamentosdespring.models.Categoria;
import com.trabajopractico.fundamentosdespring.models.Producto;

public record ProductoCreate(
        String nombre,
        double precio,
        String descripcion,
        int stock,
        String imagen,
        boolean disponible,
        Categoria categoria
) {
    public Producto toEntity(Categoria categoria){
        return new Producto(this.nombre,this.precio,this.descripcion,this.stock,this.imagen, this.disponible, categoria);
    }
}
