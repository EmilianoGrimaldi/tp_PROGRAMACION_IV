package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.models.Rol;
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
    public UsuarioDto save(UsuarioCreate usuario) {
        if (usuario.nombre() == null || usuario.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (usuario.apellido() == null || usuario.apellido().isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if (usuario.celular() == null || usuario.celular().isBlank()) {
            throw new IllegalArgumentException("El celular no puede estar vacío");
        }
        if (usuario.contrasenia() == null || usuario.contrasenia().isBlank()) {
            throw new IllegalArgumentException("La contrasenia no puede estar vacía");
        }
        if (usuario.mail() == null || usuario.mail().isBlank()) {
            throw new IllegalArgumentException("El mail no puede estar vacío");
        }
        if (usuario.rol() == null || (!usuario.rol().equals(Rol.ADMIN) && !usuario.rol().equals(Rol.USUARIO))) {
            throw new IllegalArgumentException("El rol debe ser ADMIN o USUARIO");
        }
        Usuario usuarioCreado = usuario.toEntity();

        return UsuarioDto.toDto(usuarioRepository.save(usuarioCreado));
    }

    @Override
    public UsuarioDto findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro la usuario con el id " + id ));
        return UsuarioDto.toDto(usuario);
    }

    @Override
    public List<UsuarioDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDto::toDto).toList();
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
