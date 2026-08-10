package com.trabajopractico.fundamentosdespring.detallePedido;


import com.trabajopractico.fundamentosdespring.models.DetallePedido;

public record DetallePedidoDto(
    // ERROR: El tipo del campo 'id' es 'long' (primitivo) pero en la entidad Base el campo 'id'
    // es 'Long' (wrapper). Puede causar NullPointerException si el id fuera nulo, y genera
    // inconsistencia con ProductoDto y PedidoDto que usan 'Long' y 'long' mezclados.
    long id,
    int cantidad,
    double subtotal
){
    public static DetallePedidoDto toDto(DetallePedido detallePedido){
        return new DetallePedidoDto(detallePedido.getId(), detallePedido.getCantidad(), detallePedido.getSubtotal());
    }
}