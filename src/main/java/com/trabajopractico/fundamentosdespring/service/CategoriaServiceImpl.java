package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.categoria.CategoriaCreate;
import com.trabajopractico.fundamentosdespring.categoria.CategoriaDto;
import com.trabajopractico.fundamentosdespring.categoria.CategoriaEdit;
import com.trabajopractico.fundamentosdespring.exception.ResourceNotFoundException;
import com.trabajopractico.fundamentosdespring.models.Categoria;
import com.trabajopractico.fundamentosdespring.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    @Transactional
    public CategoriaDto save(CategoriaCreate categoriaCreate) {
        if (categoriaRepository.existsByNombreIgnoreCaseAndEliminadoFalse(categoriaCreate.nombre())) {
            throw new IllegalArgumentException("Ya existe una categoría activa con el nombre: " + categoriaCreate.nombre());
        }
        Categoria categoria = categoriaCreate.toEntity();
        categoria = categoriaRepository.save(categoria);
        return CategoriaDto.toDto(categoria);
    }

    @Override
    public CategoriaDto findById(Long id) {
        Categoria categoria = categoriaRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoría activa con id " + id));
        return CategoriaDto.toDto(categoria);
    }

    @Override
    public List<CategoriaDto> findAll() {
        List<Categoria> categorias = categoriaRepository.findAllByEliminadoFalse();
        return categorias.stream().map(CategoriaDto::toDto).toList();
    }

    @Override
    @Transactional
    public CategoriaDto update(CategoriaEdit categoriaEdit, Long idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoría con id " + idCategoria));

        if (!categoria.getNombre().equalsIgnoreCase(categoriaEdit.nombre()) &&
                categoriaRepository.existsByNombreIgnoreCaseAndEliminadoFalseAndIdNot(categoriaEdit.nombre(), idCategoria)) {
            throw new IllegalArgumentException("Ya existe otra categoría activa con el nombre: " + categoriaEdit.nombre());
        }

        categoriaEdit.applyTo(categoria);
        categoria = categoriaRepository.save(categoria);
        return CategoriaDto.toDto(categoria);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoría con id " + id));
        categoria.setEliminado(true);
        categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public CategoriaDto activate(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoría con id " + id));
        if (!categoria.getEliminado()) {
            throw new IllegalArgumentException("La categoría ya está activa");
        }
        categoria.setEliminado(false);
        categoria = categoriaRepository.save(categoria);
        return CategoriaDto.toDto(categoria);
    }
}