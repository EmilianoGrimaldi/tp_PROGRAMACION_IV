package com.trabajopractico.fundamentosdespring.detallePedido;


import com.trabajopractico.fundamentosdespring.models.DetallePedido;

public record DetallePedidoDto(
    long id,
    int cantidad,
    double subtotal
){
    public static DetallePedidoDto toDto(DetallePedido detallePedido){
        return new DetallePedidoDto(detallePedido.getId(), detallePedido.getCantidad(), detallePedido.getSubtotal());
    }
}