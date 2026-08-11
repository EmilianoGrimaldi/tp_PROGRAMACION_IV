package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.categoria.CategoriaCreate;
import com.trabajopractico.fundamentosdespring.models.Categoria;
import com.trabajopractico.fundamentosdespring.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria crearCategoria(CategoriaCreate dto) {
        return categoriaRepository.save(dto.toEntity());
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }
}
