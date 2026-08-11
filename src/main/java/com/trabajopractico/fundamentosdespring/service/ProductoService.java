package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.models.Producto;
import com.trabajopractico.fundamentosdespring.producto.ProductoCreate;
import com.trabajopractico.fundamentosdespring.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto crearProducto(ProductoCreate dto) {
        return productoRepository.save(dto.toEntity());
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }
}
