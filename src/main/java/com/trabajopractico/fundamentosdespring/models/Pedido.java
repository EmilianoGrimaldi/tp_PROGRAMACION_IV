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

    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido detalle = findDetallePedidoByProducto(producto);
        if (detalle != null) {
            detalle.setCantidad(detalle.getCantidad() + cantidad);
            detalle.setSubtotal(detalle.getCantidad() * producto.getPrecio());
        } else {
            detalle = new DetallePedido(cantidad, producto);
            detalle.setPedido(this);
            this.detalles.add(detalle);
        }
        
        this.total = this.detalles.stream()
                                  .mapToDouble(DetallePedido::getSubtotal)
                                  .sum();
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto){
        if (producto == null || producto.getId() == null) return null;
        return detalles.stream()
                       .filter(d -> d.getProducto() != null && d.getProducto().getId().equals(producto.getId()))
                       .findFirst()
                       .orElse(null);
    }

    public void deleteDetallePedidoByProducto(Producto producto){
        DetallePedido detalle = findDetallePedidoByProducto(producto);
        if (detalle != null) {
            this.detalles.remove(detalle);
            detalle.setPedido(null);
            
            this.total = this.detalles.stream()
                                      .mapToDouble(DetallePedido::getSubtotal)
                                      .sum();
        }
    }
    
}
