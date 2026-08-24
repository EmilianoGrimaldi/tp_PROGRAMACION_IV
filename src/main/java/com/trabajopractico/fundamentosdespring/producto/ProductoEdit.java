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
        Categoria categoria
) {
    public void applyTo(Producto producto, Categoria categoria){
        if (this.nombre != null) {
            producto.setNombre(this.nombre);
        }

        if (this.precio != null && this.precio > 0) {
            producto.setPrecio(this.precio);
        }
        if (this.descripcion != null) {
            producto.setDescripcion(this.descripcion);
        }

        if (this.stock != null && this.stock > 0) {
            producto.setStock(this.stock);
        }
        if (this.imagen != null) {
            producto.setImagen(this.imagen);
        }

        if (this.disponible != null) {
            producto.setDisponible(this.disponible);
        }
        if (categoria != null) {
            producto.setCategoria(categoria);
        }
    }
}
