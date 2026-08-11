package com.trabajopractico.fundamentosdespring.detallePedido;

import com.trabajopractico.fundamentosdespring.models.DetallePedido;
import com.trabajopractico.fundamentosdespring.models.Producto;

public record DetallePedidoCreate(
        int cantidad,
        Producto producto
) {
    public DetallePedido toEntity(){
        return new DetallePedido(this.cantidad(), this.producto);
    }
}
