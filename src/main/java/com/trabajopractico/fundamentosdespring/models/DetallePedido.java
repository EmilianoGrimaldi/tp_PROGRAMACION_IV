package com.trabajopractico.fundamentosdespring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DetallePedido {
    // SOLO estos dos atributos, tal cual el UML
    private int cantidad;
    private Double subtotal;

    // Relaciones (necesarias para JPA, aunque no estén explícitas en el recuadro UML,
    // son obligatorias para que funcione el método addDetallePedido)
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public DetallePedido(Pedido pedido, Producto producto, int cantidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
    }
}
