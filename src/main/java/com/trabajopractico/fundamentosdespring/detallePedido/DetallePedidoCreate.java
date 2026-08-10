package com.trabajopractico.fundamentosdespring.detallePedido;

import com.trabajopractico.fundamentosdespring.models.DetallePedido;

public record DetallePedidoCreate(
        int cantidad,
        double subtotal
) {
    public DetallePedido toEntity() {
        return new DetallePedido(this.cantidad, this.subtotal);
    }
}
