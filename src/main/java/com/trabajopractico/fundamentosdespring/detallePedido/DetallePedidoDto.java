package com.trabajopractico.fundamentosdespring.detallePedido;

import com.trabajopractico.fundamentosdespring.models.DetallePedido;

public record DetallePedidoDto(
        Long id,
        Long productoId,
        String productoNombre,
        int cantidad,
        double subtotal
) {
    public static DetallePedidoDto toDto(DetallePedido detalle) {
        return new DetallePedidoDto(
                detalle.getId(),
                detalle.getProducto().getId(),
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getSubtotal()
        );
    }
}