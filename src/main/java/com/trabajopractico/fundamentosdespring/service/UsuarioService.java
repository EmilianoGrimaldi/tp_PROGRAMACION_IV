package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.usuario.*;

import java.util.List;

public interface UsuarioService {
    public UsuarioResponseDTO save(UsuarioRequestDTO usuario);
    public UsuarioResponseDTO findById(Long id);
    public List<UsuarioResponseDTO> findAll();
    public UsuarioResponseDTO update(UsuarioEdit UsuarioEdit, Long idUsuario);
    public void deleteById(Long id);
}
