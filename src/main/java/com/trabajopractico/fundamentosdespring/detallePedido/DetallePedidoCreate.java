package com.trabajopractico.fundamentosdespring.detallePedido;

import com.trabajopractico.fundamentosdespring.models.DetallePedido;

public record DetallePedidoCreate(
        int cantidad
) {
    public DetallePedido toEntity() {
        // ERROR: El 'subtotal' se recibe como dato de entrada desde el cliente.
        // Esto es un error de diseño: el subtotal debería calcularse en el servidor
        // (cantidad * precio del producto), no ser enviado por el consumidor de la API,
        // ya que permite manipular precios desde el exterior.
        return new DetallePedido(this.cantidad);
    }
}
