package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.usuario.UsuarioDto;
import com.trabajopractico.fundamentosdespring.models.Usuario;
import com.trabajopractico.fundamentosdespring.repository.UsuarioRepository;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioCreate;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioEdit;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDto save(UsuarioCreate UsuarioCreate) {
        Usuario usuario = UsuarioCreate.toEntity();
        usuario = usuarioRepository.save(usuario);
        return UsuarioDto.toDto(usuario);
    }

    @Override
    public UsuarioDto findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro la usuario con el id " + id ));
        return UsuarioDto.toDto(usuario);
    }

    @Override
    public List<UsuarioDto> findAll() {
        List<Usuario> usuario = usuarioRepository.findAll();
        return usuario.stream().map(UsuarioDto::toDto).toList();
    }

    @Override
    public UsuarioDto update(UsuarioEdit UsuarioEdit, Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new NullPointerException("No se encontro la usuario con el id " + idUsuario ));
        UsuarioEdit.applyTo(usuario);
        usuario = usuarioRepository.save(usuario);
        return UsuarioDto.toDto(usuario);
    }

    @Override
    public void deleteById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro la usuario con el id " + id ));
        usuario.setEliminado(true);
        usuarioRepository.save(usuario);
    }
}
