package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.exception.ResourceNotFoundException;
import com.trabajopractico.fundamentosdespring.models.Pedido;
import com.trabajopractico.fundamentosdespring.models.Usuario;
import com.trabajopractico.fundamentosdespring.pedido.PedidoDto;
import com.trabajopractico.fundamentosdespring.pedido.PedidoEdit;
import com.trabajopractico.fundamentosdespring.repository.PedidoRepository;
import com.trabajopractico.fundamentosdespring.repository.UsuarioRepository;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public PedidoDto save(PedidoEdit pedidoEdit) {
        // Resolver el usuario a partir del usuarioId del DTO
        if (pedidoEdit.usuarioId() == null) {
            throw new IllegalArgumentException("El usuarioId es obligatorio para crear un pedido");
        }
        Usuario usuario = usuarioRepository.findById(pedidoEdit.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró el usuario con id " + pedidoEdit.usuarioId()));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setTotal(0.0);
        pedidoEdit.applyTo(pedido);

        pedidoRepository.save(pedido);
        return PedidoDto.toDto(pedido);
    }

    @Override
    public PedidoDto findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el pedido con id " + id));
        return PedidoDto.toDto(pedido);
    }

    @Override
    public List<PedidoDto> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .filter(p -> p.getEliminado() == null || !p.getEliminado())
                .map(PedidoDto::toDto)
                .toList();
    }

    @Override
    public PedidoDto update(PedidoEdit pedidoEdit, Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el pedido con id " + idPedido));
        pedidoEdit.applyTo(pedido);
        pedido = pedidoRepository.save(pedido);
        return PedidoDto.toDto(pedido);
    }

    @Override
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
