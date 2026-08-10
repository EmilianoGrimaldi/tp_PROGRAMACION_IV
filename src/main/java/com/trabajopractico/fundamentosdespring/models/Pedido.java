package com.trabajopractico.fundamentosdespring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido extends Base{
    private LocalDate fecha;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private Double total;
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles = new ArrayList<>();

    /**
     * Agrega un nuevo detalle a este pedido o incrementa la cantidad si el producto ya existe.
     *
     * Pasos a implementar:
     *   1. Buscar en la lista 'detalles' si ya existe un DetallePedido con el mismo producto
     *      (usar findDetallePedidoByProducto o un stream con filter).
     *
     *   2a. Si el producto YA existe en la lista:
     *       - Obtener ese DetallePedido existente.
     *       - Incrementar su cantidad: detalle.setCantidad(detalle.getCantidad() + cantidad).
     *       - Recalcular su subtotal: detalle.setSubtotal(detalle.getCantidad() * producto.getPrecio()).
     *
     *   2b. Si el producto NO existe en la lista:
     *       - Crear un nuevo DetallePedido: new DetallePedido(cantidad, producto).
     *       - Establecer la referencia al pedido actual: detalle.setPedido(this).
     *       - Agregar el detalle a la lista: detalles.add(detalle).
     *
     *   3. Recalcular el total del pedido sumando todos los subtotales:
     *      this.total = detalles.stream()
     *                          .mapToDouble(DetallePedido::getSubtotal)
     *                          .sum();
     */
    public void addDetallePedido(int cantidad, Producto producto) {
        // TODO: implementar según la documentación del método
    }

    /**
     * Busca y retorna el DetallePedido de este pedido que corresponde al producto dado.
     * Retorna null si no existe un detalle para ese producto.
     * NOTA: El nombre tiene un typo ('findeDetallePedidoByProducto'), corregir a 'findDetallePedidoByProducto'.
     *
     * Pasos a implementar:
     *   1. Iterar la lista 'detalles' buscando aquel cuyo producto coincida con el recibido.
     *      Comparar por ID: detalle.getProducto().getId().equals(producto.getId()).
     *
     *   2a. Si se encuentra un DetallePedido que coincide: retornarlo.
     *
     *   2b. Si no se encuentra ninguno: retornar null
     *      (o mejor aún, cambiar el tipo de retorno a Optional<DetallePedido> y retornar Optional.empty()).
     *
     *   Ejemplo con stream:
     *      return detalles.stream()
     *                     .filter(d -> d.getProducto().getId().equals(producto.getId()))
     *                     .findFirst()
     *                     .orElse(null);
     */
    public DetallePedido findDetallePedidoByProducto(Producto producto){
        // TODO: implementar según la documentación del método
        return null;
    }

    /**
     * Elimina de este pedido el DetallePedido asociado al producto dado y recalcula el total.
     *
     * Pasos a implementar:
     *   1. Buscar el DetallePedido correspondiente al producto (usar findDetallePedidoByProducto).
     *
     *   2. Si NO se encuentra ningún detalle para ese producto:
     *      - Opción A: no hacer nada (silent no-op).
     *      - Opción B: lanzar una excepción (ej: IllegalArgumentException("Producto no encontrado en el pedido")).
     *
     *   3. Si se encuentra el detalle:
     *      - Eliminarlo de la lista: detalles.remove(detalle).
     *      - Establecer la referencia al pedido como null: detalle.setPedido(null).
     *        (Esto es necesario si 'orphanRemoval = true' no lo maneja automáticamente).
     *
     *   4. Recalcular el total del pedido:
     *      this.total = detalles.stream()
     *                          .mapToDouble(DetallePedido::getSubtotal)
     *                          .sum();
     *      Si la lista queda vacía, el total debe ser 0.0.
     */
    public void deleteDetallePedidoByProducto(Producto producto){
        // TODO: implementar según la documentación del método
    }
    
}
