package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.usuario.*;
import com.trabajopractico.fundamentosdespring.models.Usuario;
import com.trabajopractico.fundamentosdespring.repository.UsuarioRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioResponseDTO save(UsuarioRequestDTO usuario) {
        Usuario usuarioCreado = Usuario.builder().nombre(usuario.nombre()).apellido(usuario.apellido()).mail(usuario.mail())
                .celular(usuario.celular()).contrasenia(usuario.contrasenia()).rol(usuario.rol()).build();
        return UsuarioResponseDTO.fromEntity(usuarioRepository.save(usuarioCreado));
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public UsuarioResponseDTO update(UsuarioEdit UsuarioEdit, Long idUsuario) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    /*@Override
    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro la usuario con el id " + id ));
        return UsuarioDto.toDto(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuario = usuarioRepository.findAll();
        return usuario.stream().map(UsuarioDto::toDto).toList();
    }

    @Override
    public UsuarioResponseDTO update(UsuarioEdit UsuarioEdit, Long idUsuario) {
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
    }*/
}
