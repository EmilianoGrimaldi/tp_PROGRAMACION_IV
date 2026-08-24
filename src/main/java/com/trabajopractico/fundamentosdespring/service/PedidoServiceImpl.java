package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.detallePedido.DetallePedidoCreate;
import com.trabajopractico.fundamentosdespring.exception.ResourceNotFoundException;
import com.trabajopractico.fundamentosdespring.models.DetallePedido;
import com.trabajopractico.fundamentosdespring.models.Pedido;
import com.trabajopractico.fundamentosdespring.models.Producto;
import com.trabajopractico.fundamentosdespring.models.Usuario;
import com.trabajopractico.fundamentosdespring.pedido.PedidoDto;
import com.trabajopractico.fundamentosdespring.pedido.PedidoEdit;
import com.trabajopractico.fundamentosdespring.repository.PedidoRepository;
import com.trabajopractico.fundamentosdespring.repository.ProductoRepository;
import com.trabajopractico.fundamentosdespring.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional
    public PedidoDto save(PedidoEdit pedidoEdit) {
        Usuario usuario = usuarioRepository.findByIdAndEliminadoFalse(pedidoEdit.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado o eliminado"));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setTotal(0.0);
        pedidoEdit.applyTo(pedido);

        List<DetallePedido> detalles = new ArrayList<>();
        double total = 0.0;

        for (DetallePedidoCreate detDto : pedidoEdit.detalles()) {
            Producto producto = productoRepository.findByIdAndEliminadoFalse(detDto.productoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado o eliminado"));

            if (producto.getStock() < detDto.cantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - detDto.cantidad());
            productoRepository.save(producto);

            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto);
            detalle.setCantidad(detDto.cantidad());
            detalle.setSubtotal(producto.getPrecio() * detDto.cantidad());
            detalle.setPedido(pedido);
            detalles.add(detalle);

            total += detalle.getSubtotal();
        }

        pedido.setDetalles(detalles);
        pedido.setTotal(total);
        pedido = pedidoRepository.save(pedido);
        return PedidoDto.toDto(pedido);
    }

    @Override
    public PedidoDto findById(Long id) {
        Pedido pedido = pedidoRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el pedido activo con id " + id));
        return PedidoDto.toDto(pedido);
    }

    @Override
    public List<PedidoDto> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAllByEliminadoFalse();
        return pedidos.stream().map(PedidoDto::toDto).toList();
    }

    @Override
    @Transactional
    public PedidoDto update(PedidoEdit pedidoEdit, Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el pedido con id " + idPedido));
        pedidoEdit.applyTo(pedido);
        pedido = pedidoRepository.save(pedido);
        return PedidoDto.toDto(pedido);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el pedido con id " + id));
        pedido.setEliminado(true);
        pedidoRepository.save(pedido);
    }

    @Override
    @Transactional
    public PedidoDto activate(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el pedido con id " + id));
        if (!pedido.getEliminado()) {
            throw new IllegalArgumentException("El pedido ya está activo");
        }
        pedido.setEliminado(false);
        pedido = pedidoRepository.save(pedido);
        return PedidoDto.toDto(pedido);
    }
}