package com.trabajopractico.fundamentosdespring.repository;

import com.trabajopractico.fundamentosdespring.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByIdAndEliminadoFalse(Long id);
    List<Pedido> findAllByEliminadoFalse();
}
