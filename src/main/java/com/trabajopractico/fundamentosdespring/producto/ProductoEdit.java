package com.trabajopractico.fundamentosdespring.producto;

import com.trabajopractico.fundamentosdespring.models.Categoria;
import com.trabajopractico.fundamentosdespring.models.Producto;

public record ProductoEdit(
        String nombre,
        Double precio,
        String descripcion,
        Integer stock,
        String imagen,
        Boolean disponible,
        Long categoriaId
) {
    public void applyTo(Producto producto, Long categoriaId){
        if (this.nombre != null){
            producto.setNombre(this.nombre);
        }
        if (this.precio > 0){
            producto.setPrecio(this.precio);
        }
        if (this.descripcion != null){
            producto.setDescripcion(this.descripcion);
        }
        if (this.stock > 0){
            producto.setStock(this.stock);
        }
        if (this.imagen != null){
            producto.setImagen(this.imagen);
        }
        if (this.disponible){
            producto.setDisponible(this.disponible);
        }
        if (this.categoriaId != null){
            producto.setCategoriaId(this.categoriaId);
        }
    }
}
