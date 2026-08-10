package com.trabajopractico.fundamentosdespring.pedido;

import com.trabajopractico.fundamentosdespring.detallePedido.DetallePedidoDto;
import com.trabajopractico.fundamentosdespring.models.*;

import java.time.LocalDate;
import java.util.List;

public record PedidoDto(
        long id,
        LocalDate fecha,
        Estado estado,
        Double total,
        FormaPago formaPago,
        Usuario usuario,
        List<DetallePedido> detalles
) {
    public static PedidoDto toDto(Pedido pedido){
        return new PedidoDto(pedido.getId(),pedido.getFecha(), pedido.getEstado(), pedido.getTotal(), pedido.getFormaPago(),  pedido.getUsuario(), pedido.getDetalles());
    }
}
