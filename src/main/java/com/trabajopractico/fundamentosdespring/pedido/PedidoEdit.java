package com.trabajopractico.fundamentosdespring.pedido;

import com.trabajopractico.fundamentosdespring.models.Estado;
import com.trabajopractico.fundamentosdespring.models.FormaPago;
import com.trabajopractico.fundamentosdespring.models.Pedido;

import java.time.LocalDate;

public record PedidoEdit (
        LocalDate fecha,
        Estado estado,
        Double total,
        FormaPago formaPago
) {
    public void applyTo(Pedido pedido){
        if(pedido.getFecha() != null){
            pedido.setFecha(pedido.getFecha());
        }
        if (this.estado != null){
            pedido.setEstado(this.estado);
        }
        if (this.total != null){
            pedido.setTotal(this.total);
        }
        if (this.formaPago != null){
            pedido.setFormaPago(this.formaPago);
        }
    }
}
