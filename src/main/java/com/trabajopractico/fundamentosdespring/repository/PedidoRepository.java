package com.trabajopractico.fundamentosdespring.repository;

import com.trabajopractico.fundamentosdespring.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
