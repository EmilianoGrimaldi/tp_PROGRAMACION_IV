package com.trabajopractico.fundamentosdespring.pedido;

import com.trabajopractico.fundamentosdespring.models.*;

import java.time.LocalDate;

public record PedidoDto(
        Long id,
        LocalDate fecha,
        Estado estado,
        Double total,
        FormaPago formaPago
) {
    public static PedidoDto toDto(Pedido pedido){
        return new PedidoDto(pedido.getId(),pedido.getFecha(), pedido.getEstado(), pedido.getTotal(),pedido.getFormaPago());
    }
}
