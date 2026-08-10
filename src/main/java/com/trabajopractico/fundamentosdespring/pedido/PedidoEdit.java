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
        // ERROR: La condición revisa 'pedido.getFecha()' (la fecha actual del objeto existente)
        // en lugar de 'this.fecha' (la nueva fecha recibida en el DTO de edición).
        // Esto hace que la fecha nunca se actualice si el pedido ya tiene una fecha asignada,
        // o que se ejecute el bloque cuando no debería.
        if(this.fecha != null){
            // ERROR: Self-assignment: se sobreescribe 'pedido.fecha' con el mismo valor 'pedido.getFecha()'.
            // Nunca se usa 'this.fecha' para actualizar el pedido. El nuevo valor del DTO es ignorado.
            pedido.setFecha(this.fecha);
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
