package com.trabajopractico.fundamentosdespring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria extends Base{
    private String nombre;
    private String descripcion;
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos = new ArrayList<>();

    public Categoria(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}
