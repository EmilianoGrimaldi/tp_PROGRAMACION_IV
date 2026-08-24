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
    public ProductoDto save(ProductoCreate productoCreate) {
        // 1. Buscar la categoría por ID
        Long categoriaId = productoCreate.categoriaId();
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró la categoría con id: " + categoriaId));

        // 2. Validar unicidad del nombre (evita duplicados)
        if (productoRepository.existsByNombreIgnoreCase(productoCreate.nombre())) {
            throw new IllegalArgumentException(
                    "Ya existe un producto con el nombre: " + productoCreate.nombre());
        }

        // 3. Ajustar disponibilidad según stock (decisión de negocio)
        boolean disponibleFinal = productoCreate.disponible();
        if (productoCreate.stock() == 0 && disponibleFinal) {
            disponibleFinal = false;
        }

        // 4. Crear y guardar la entidad
        Producto producto = productoCreate.toEntity(categoria);
        producto.setDisponible(disponibleFinal);
        productoRepository.save(producto);
        return ProductoDto.toDto(producto);
    }

    @Override
    public ProductoDto findById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con id " + id));
        return ProductoDto.toDto(producto);
    }

    @Override
    public List<ProductoDto> findAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .filter(p -> p.getEliminado() == null || !p.getEliminado())
                .map(ProductoDto::toDto)
                .toList();
    }

    @Override
    public ProductoDto update(ProductoEdit productoEdit, Long idProducto) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con id " + idProducto));

        Categoria categoria = null;
        if (productoEdit.categoria() != null) {
            Long catId = productoEdit.categoria().getId();
            categoria = categoriaRepository.findById(catId)
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoría con id " + catId));
        }
        productoEdit.applyTo(producto, categoria);
        productoRepository.save(producto);
        return ProductoDto.toDto(producto);
    }

    @Override
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
