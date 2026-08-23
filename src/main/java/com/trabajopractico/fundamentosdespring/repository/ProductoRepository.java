package com.trabajopractico.fundamentosdespring.repository;

import com.trabajopractico.fundamentosdespring.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
}
