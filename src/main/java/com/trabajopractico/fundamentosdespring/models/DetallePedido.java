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
public class DetallePedido extends Base{
    private int cantidad;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public DetallePedido(int cantidad) {
        this.cantidad = cantidad;
        // ERROR NUEVO: NullPointerException en runtime. El campo 'producto' es null en este punto
        // porque es asignado por JPA, no por el constructor. Llamar a 'producto.getPrecio()' aquí
        // lanzará NullPointerException siempre que se use este constructor.
        // El subtotal debe calcularse externamente, donde ya se tenga el objeto Producto disponible,
        // o bien recibir el Producto como parámetro en este constructor.
        this.subtotal = cantidad * producto.getPrecio();
    }


}
