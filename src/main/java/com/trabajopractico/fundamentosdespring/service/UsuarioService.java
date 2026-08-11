package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.usuario.UsuarioCreate;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioDto;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioEdit;

import java.util.List;

public interface UsuarioService {
    public UsuarioDto save(UsuarioCreate UsuarioCreate);
    public UsuarioDto findById(Long id);
    public List<UsuarioDto> findAll();
    public UsuarioDto update(UsuarioEdit UsuarioEdit, Long idUsuario);
    public void deleteById(Long id);
}
