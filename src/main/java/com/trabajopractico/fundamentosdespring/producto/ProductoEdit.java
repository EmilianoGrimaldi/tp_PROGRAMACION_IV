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
    public void applyTo(Producto producto){
        if (this.nombre != null){
            producto.setNombre(this.nombre);
        }
        if (this.precio > 0){
            producto.setPrecio(this.precio);
            // ERROR (design): 'precio' es un primitivo 'double', por lo que su valor por defecto es 0.0.
            // Si no se envía precio en el request (campo omitido), igual entra con 0.0 y la condición
            // lo rechaza correctamente. Sin embargo, no permite intencionalmente setear el precio a 0.
            // Se debería usar el tipo wrapper 'Double' (nullable) para distinguir entre "no enviado" y "0".
        }
        if (this.descripcion != null){
            producto.setDescripcion(this.descripcion);
        }
        if (this.stock > 0){
            producto.setStock(this.stock);
            // ERROR (design): Mismo problema que 'precio': 'stock' es 'int' (primitivo).
            // No se puede distinguir entre "stock no enviado" (0 por defecto) y "stock intencional de 0".
            // Usar el tipo wrapper 'Integer' permitiría aceptar stock = 0 como valor válido.
        }
        if (this.imagen != null){
            producto.setImagen(this.imagen);
        }
        // ERROR: 'disponible' es un primitivo 'boolean' (por defecto false).
        // Si el cliente no envía este campo, el record lo recibe como false.
        // La condición 'if (this.disponible)' solo actualiza el campo si es true,
        // lo que significa que NUNCA se puede marcar un producto como NO disponible (false) mediante edición.
        // Se debería usar el wrapper 'Boolean' (nullable) para manejar correctamente los tres estados:
        // no enviado (null), true y false.
        if (this.disponible){
            producto.setDisponible(this.disponible);
        }
        if (this.categoria != null){
            producto.setCategoria(this.categoria);
        }
    }
}
