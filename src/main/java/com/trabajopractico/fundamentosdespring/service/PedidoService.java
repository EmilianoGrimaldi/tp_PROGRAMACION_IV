package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.pedido.PedidoDto;
import com.trabajopractico.fundamentosdespring.pedido.PedidoEdit;

import java.util.List;

public interface PedidoService {
    public PedidoDto save(PedidoEdit pedidoEdit);

    public PedidoDto findById(Long id);

    public List<PedidoDto> findAll();

    public PedidoDto update(PedidoEdit pedidoEdit, Long idPedido);

    public void deleteById(Long id);
}
