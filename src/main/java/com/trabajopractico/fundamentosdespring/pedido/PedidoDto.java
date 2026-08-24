package com.trabajopractico.fundamentosdespring.pedido;

import com.trabajopractico.fundamentosdespring.detallePedido.DetallePedidoDto;
import com.trabajopractico.fundamentosdespring.models.Estado;
import com.trabajopractico.fundamentosdespring.models.FormaPago;
import com.trabajopractico.fundamentosdespring.models.Pedido;

import java.time.LocalDate;
import java.util.List;

public record PedidoDto(
        Long id,
        LocalDate fecha,
        Estado estado,
        Double total,
        FormaPago formaPago,
        Long usuarioId,
        List<DetallePedidoDto> detalles
) {
    public static PedidoDto toDto(Pedido pedido) {
        List<DetallePedidoDto> detallesDto = pedido.getDetalles().stream()
                .map(DetallePedidoDto::toDto)
                .toList();
        return new PedidoDto(
                pedido.getId(),
                pedido.getFecha(),
                pedido.getEstado(),
                pedido.getTotal(),
                pedido.getFormaPago(),
                pedido.getUsuario().getId(),
                detallesDto
        );
    }
}