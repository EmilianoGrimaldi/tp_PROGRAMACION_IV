package com.trabajopractico.fundamentosdespring.repository;

import com.trabajopractico.fundamentosdespring.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
    // Para buscar solo activos
    Optional<Producto> findByIdAndEliminadoFalse(Long id);
    List<Producto> findAllByEliminadoFalse();

    // Validaciones de unicidad
    boolean existsByNombreIgnoreCaseAndEliminadoFalse(String nombre);
    boolean existsByNombreIgnoreCaseAndEliminadoFalseAndIdNot(String nombre, Long id);
}
