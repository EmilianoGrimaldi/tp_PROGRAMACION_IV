package com.trabajopractico.fundamentosdespring.pedido;

import com.trabajopractico.fundamentosdespring.detallePedido.DetallePedidoCreate;
import com.trabajopractico.fundamentosdespring.models.Estado;
import com.trabajopractico.fundamentosdespring.models.FormaPago;
import com.trabajopractico.fundamentosdespring.models.Pedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record PedidoEdit(
        LocalDate fecha,
        Estado estado,
        Double total,
        FormaPago formaPago,
        Long usuarioId,

        @NotEmpty(message = "El pedido debe tener al menos un detalle")
        @Valid
        List<DetallePedidoCreate> detalles  // ← NUEVO
) {
    public void applyTo(Pedido pedido) {
        if (this.fecha != null) {
            pedido.setFecha(this.fecha);
        }
        if (this.estado != null) {
            pedido.setEstado(this.estado);
        }
        if (this.total != null) {
            pedido.setTotal(this.total);
        }
        if (this.formaPago != null) {
            pedido.setFormaPago(this.formaPago);
        }
    }
}