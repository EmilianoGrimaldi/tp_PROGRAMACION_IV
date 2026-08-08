package com.trabajopractico.fundamentosdespring.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido {
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;

    public void addDetallePedido(int cantidad, Producto producto){

    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto){
        DetallePedido dp = new DetallePedido();
        return dp;
    }

    public void deleteDetallePedidoByProducto(Producto producto){

    }

}
