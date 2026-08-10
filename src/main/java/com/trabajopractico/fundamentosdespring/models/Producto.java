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
public class Producto extends Base {
    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
