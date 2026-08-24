package com.trabajopractico.fundamentosdespring.detallePedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetallePedidoCreate(
        @NotNull(message = "El ID del producto es obligatorio")
        Long productoId,

        @Min(value = 1, message = "La cantidad debe ser mayor a 0")
        int cantidad
) {}