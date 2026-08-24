package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.exception.ResourceNotFoundException;
import com.trabajopractico.fundamentosdespring.models.Categoria;
import com.trabajopractico.fundamentosdespring.models.Producto;
import com.trabajopractico.fundamentosdespring.producto.ProductoCreate;
import com.trabajopractico.fundamentosdespring.producto.ProductoDto;
import com.trabajopractico.fundamentosdespring.producto.ProductoEdit;
import com.trabajopractico.fundamentosdespring.repository.CategoriaRepository;
import com.trabajopractico.fundamentosdespring.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    @Transactional
    public ProductoDto save(ProductoCreate productoCreate) {
        // 1. Buscar categoría activa
        Long categoriaId = productoCreate.categoriaId();
        Categoria categoria = categoriaRepository.findByIdAndEliminadoFalse(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoría activa con id: " + categoriaId));

        // 2. Validar unicidad de nombre entre productos activos
        if (productoRepository.existsByNombreIgnoreCaseAndEliminadoFalse(productoCreate.nombre())) {
            throw new IllegalArgumentException("Ya existe un producto activo con el nombre: " + productoCreate.nombre());
        }

        // 3. Ajustar disponibilidad según stock
        boolean disponibleFinal = productoCreate.disponible();
        if (productoCreate.stock() == 0 && disponibleFinal) {
            disponibleFinal = false;
        }

        // 4. Crear y guardar
        Producto producto = productoCreate.toEntity(categoria);
        producto.setDisponible(disponibleFinal);
        producto = productoRepository.save(producto);
        return ProductoDto.toDto(producto);
    }

    @Override
    public ProductoDto findById(Long id) {
        Producto producto = productoRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto activo con id " + id));
        return ProductoDto.toDto(producto);
    }

    @Override
    public List<ProductoDto> findAll() {
        List<Producto> productos = productoRepository.findAllByEliminadoFalse();
        return productos.stream().map(ProductoDto::toDto).toList();
    }

    @Override
    @Transactional
    public ProductoDto update(ProductoEdit productoEdit, Long idProducto) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con id " + idProducto));

        if (!producto.getNombre().equalsIgnoreCase(productoEdit.nombre()) &&
                productoRepository.existsByNombreIgnoreCaseAndEliminadoFalseAndIdNot(productoEdit.nombre(), idProducto)) {
            throw new IllegalArgumentException("Ya existe otro producto activo con el nombre: " + productoEdit.nombre());
        }

        Categoria categoria = null;
        if (productoEdit.categoria() != null) {
            Long catId = productoEdit.categoria().getId();
            categoria = categoriaRepository.findByIdAndEliminadoFalse(catId)
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoría activa con id " + catId));
        }
        productoEdit.applyTo(producto, categoria);
        producto = productoRepository.save(producto);
        return ProductoDto.toDto(producto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con id " + id));
        producto.setEliminado(true);
        productoRepository.save(producto);
    }

    @Override
    @Transactional
    public ProductoDto activate(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con id " + id));
        if (!producto.getEliminado()) {
            throw new IllegalArgumentException("El producto ya está activo");
        }
        producto.setEliminado(false);
        producto = productoRepository.save(producto);
        return ProductoDto.toDto(producto);
    }
}