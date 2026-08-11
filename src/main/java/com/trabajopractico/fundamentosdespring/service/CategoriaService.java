package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.categoria.CategoriaCreate;
import com.trabajopractico.fundamentosdespring.categoria.CategoriaDto;
import com.trabajopractico.fundamentosdespring.categoria.CategoriaEdit;
import com.trabajopractico.fundamentosdespring.models.Categoria;

import java.util.List;

public interface CategoriaService {
    public CategoriaDto save(CategoriaCreate categoriaCreate);
    public CategoriaDto findById(Long id);
    public List<CategoriaDto> findAll();
    public CategoriaDto update(CategoriaEdit categoriaEdit, Long idCategoria);
    public void deleteById(Long id);
}
