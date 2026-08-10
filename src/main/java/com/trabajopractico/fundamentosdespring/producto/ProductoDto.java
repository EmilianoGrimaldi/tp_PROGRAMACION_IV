package com.trabajopractico.fundamentosdespring.producto;

import com.trabajopractico.fundamentosdespring.models.Producto;

public record ProductoDto (
        Long id,
        String nombre,
        double precio,
        String descripcion,
        int stock,
        String imagen,
        boolean disponible
){

    public static ProductoDto toDto(Producto producto){
        return new ProductoDto(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getDescripcion(), producto.getStock(), producto.getImagen(), producto.isDisponible());
    }
}
