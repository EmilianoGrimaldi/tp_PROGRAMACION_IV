package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.detallePedido.DetallePedidoCreate;
import com.trabajopractico.fundamentosdespring.detallePedido.DetallePedidoDto;
import com.trabajopractico.fundamentosdespring.models.Estado;
import com.trabajopractico.fundamentosdespring.models.Pedido;
import com.trabajopractico.fundamentosdespring.models.Producto;
import com.trabajopractico.fundamentosdespring.models.Usuario;
import com.trabajopractico.fundamentosdespring.repository.PedidoRepository;
import com.trabajopractico.fundamentosdespring.repository.ProductoRepository;
import com.trabajopractico.fundamentosdespring.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ProductoRepository productoRepository,
                         UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Pedido crearPedidoConDetalles(Usuario usuario, List<DetallePedidoCreate> detalles) {
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFecha(LocalDate.now());
        pedido.setEstado(Estado.CONFIRMADO);
        
        for (DetallePedidoCreate detalle : detalles) {
            Producto producto = productoRepository.findById(detalle.producto().getId()).orElse(null);
            if (producto != null) {
                pedido.addDetallePedido(detalle.cantidad(), producto);
            }
        }
        
        return pedidoRepository.save(pedido);
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
