package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.models.Usuario;
import com.trabajopractico.fundamentosdespring.repository.UsuarioRepository;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioCreate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario crearUsuario(UsuarioCreate dto) {
        return usuarioRepository.save(dto.toEntity());
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}
