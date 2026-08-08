package com.trabajopractico.fundamentosdespring.detallePedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDTO {
    private int cantidad;
    private double subtotal;
}
