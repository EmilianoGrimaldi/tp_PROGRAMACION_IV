package com.trabajopractico.fundamentosdespring.pedido;

import com.trabajopractico.fundamentosdespring.models.Estado;
import com.trabajopractico.fundamentosdespring.models.FormaPago;
import com.trabajopractico.fundamentosdespring.models.Pedido;

import java.time.LocalDate;

public record PedidoEdit(
        LocalDate fecha,
        Estado estado,
        Double total,
        FormaPago formaPago,
        Long usuarioId  // usado al crear; ignorado (null) en updates
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
