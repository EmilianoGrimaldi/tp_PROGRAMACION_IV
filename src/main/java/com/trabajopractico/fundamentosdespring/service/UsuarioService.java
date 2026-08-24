package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.usuario.*;

import java.util.List;

public interface UsuarioService {
    public UsuarioDto save(UsuarioCreate usuario);
    public UsuarioDto findById(Long id);
    public UsuarioDto findByMail(String mail);
    public List<UsuarioDto> findAll();
    public UsuarioDto update(UsuarioEdit UsuarioEdit, Long idUsuario);
    public void deleteById(Long id);
    public UsuarioDto activate(Long id);
}
