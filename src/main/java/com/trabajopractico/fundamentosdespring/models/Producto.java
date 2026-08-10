package com.trabajopractico.fundamentosdespring.models;

// NOTA: El import de 'ProductoDto' ya fue eliminado correctamente.
// ERROR NUEVO: El constructor con parámetros fue eliminado, pero 'ProductoCreate.toEntity()'
// llama a 'new Producto(nombre, precio, descripcion, stock, imagen, disponible, categoria)'.
// Lombok @AllArgsConstructor genera un constructor solo con los campos propios de la clase (no heredados),
// que coincide con esa firma (7 parámetros), por lo que actualmente compila correctamente.
// Sin embargo, si en el futuro se agregan campos a Producto o a Base, este comportamiento puede cambiar.
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
