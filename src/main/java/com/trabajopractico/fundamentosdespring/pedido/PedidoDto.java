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
        // ERROR: En la línea 22, se llama a 'UsuarioDto.toDto(pedido.getUsuario())'.
        // Pero el tipo de retorno de ese método está declarado como 'Usuario' (no 'UsuarioDto'),
        // lo que produce un error de compilación al intentar asignarlo al campo 'usuario' (también
        // incorrecto, ver arriba). Ambos errores están encadenados.
        return new PedidoDto(pedido.getId(),pedido.getFecha(), pedido.getEstado(), pedido.getTotal(),pedido.getFormaPago());
    }
}
