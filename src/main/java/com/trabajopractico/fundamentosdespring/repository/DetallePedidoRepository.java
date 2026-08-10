package com.trabajopractico.fundamentosdespring.repository;

import com.trabajopractico.fundamentosdespring.models.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// ERROR CRÍTICO: El repositorio se llama 'DetallePedidoRepository' pero está configurado
// para trabajar con la entidad 'Usuario' en lugar de 'DetallePedido'.
// JpaRepository<Usuario, Long> es incorrecto: debería ser JpaRepository<DetallePedido, Long>.
// Además el import de 'Usuario' en la línea 3 es incorrecto; debería importar 'DetallePedido'.
public interface DetallePedidoRepository  extends JpaRepository<DetallePedido, Long> {
}
