package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.models.Categoria;
import com.trabajopractico.fundamentosdespring.models.Producto;
import com.trabajopractico.fundamentosdespring.producto.ProductoCreate;
import com.trabajopractico.fundamentosdespring.producto.ProductoDto;
import com.trabajopractico.fundamentosdespring.producto.ProductoEdit;
import com.trabajopractico.fundamentosdespring.repository.CategoriaRepository;
import com.trabajopractico.fundamentosdespring.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ProductoServiceImpl implements ProductoService{
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public ProductoDto save(ProductoCreate productoCreate) {
        Categoria categoria = categoriaRepository.findById(productoCreate.categoria().getId()).orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + id ));
        Producto producto = productoCreate.toEntity(categoria);
        productoRepository.save(producto);
        return ProductoDto.toDto(producto);
    }

    @Override
    public ProductoDto findById(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro la producto con el id " + id ));
        return ProductoDto.toDto(producto);
    }

    @Override
    public List<ProductoDto> findAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(ProductoDto::toDto).toList();
    }

    @Override
    public ProductoDto update(ProductoEdit productoEdit, Long idProducto) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow(() -> new NullPointerException("No se encontro la producto con el id " + idProducto ));

        Categoria categoria = null;
        if (productoEdit.categoriaId() != null){
            categoria = categoriaRepository.findById(productoEdit.categoriaId()).orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + id));
        }
        productoEdit.applyTo(producto, categoria);
        return ProductoDto.toDto(producto);
    }

    @Override
    public void deleteById(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro la producto con el id " + id ));
        producto.setEliminado(true);
        productoRepository.save(producto);
    }
}
