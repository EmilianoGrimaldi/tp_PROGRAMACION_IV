package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.pedido.PedidoDto;
import com.trabajopractico.fundamentosdespring.models.Pedido;
import com.trabajopractico.fundamentosdespring.pedido.PedidoEdit;
import com.trabajopractico.fundamentosdespring.repository.PedidoRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public PedidoDto save(Pedido pedido) {
        pedidoRepository.save(pedido);
        return PedidoDto.toDto(pedido);
    }

    @Override
    public PedidoDto findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro la pedido con el id " + id));
        return PedidoDto.toDto(pedido);
    }

    @Override
    public List<PedidoDto> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(PedidoDto::toDto).toList();
    }

    @Override
    public PedidoDto update(PedidoEdit pedidoEdit, Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new NullPointerException("No se encontro la pedido con el id " + idPedido));
        pedidoEdit.applyTo(pedido);
        pedido = pedidoRepository.save(pedido);
        return PedidoDto.toDto(pedido);
    }

    @Override
    public void deleteById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro la pedido con el id " + id));
        pedido.setEliminado(true);
        pedidoRepository.save(pedido);
    }
}
