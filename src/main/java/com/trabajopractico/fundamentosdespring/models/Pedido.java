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
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles = new ArrayList<>();

    public void addDetallePedido(int cantidad, Producto producto) {
        //DetallePedido detalle = new DetallePedido(this, producto, cantidad);
        //detalles.add(detalle);
        // calcular subtotal y actualizar total
    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto){
        DetallePedido dp = new DetallePedido();
        return dp;
    }

    public void deleteDetallePedidoByProducto(Producto producto){

    }

}
