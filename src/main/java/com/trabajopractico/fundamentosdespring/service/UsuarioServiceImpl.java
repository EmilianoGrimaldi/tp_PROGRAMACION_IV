package com.trabajopractico.fundamentosdespring.service;

import com.trabajopractico.fundamentosdespring.exception.ResourceNotFoundException;
import com.trabajopractico.fundamentosdespring.models.Rol;
import com.trabajopractico.fundamentosdespring.models.Usuario;
import com.trabajopractico.fundamentosdespring.repository.UsuarioRepository;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioCreate;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioDto;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioEdit;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public UsuarioDto save(UsuarioCreate usuario) {
        // Validaciones de campos obligatorios
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
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (usuario.mail() == null || usuario.mail().isBlank()) {
            throw new IllegalArgumentException("El mail no puede estar vacío");
        }
        if (usuario.rol() == null || (!usuario.rol().equals(Rol.ADMIN) && !usuario.rol().equals(Rol.USUARIO))) {
            throw new IllegalArgumentException("El rol debe ser ADMIN o USUARIO");
        }

        // Validar unicidad de mail entre usuarios activos
        if (usuarioRepository.existsByMailAndEliminadoFalse(usuario.mail())) {
            throw new IllegalArgumentException("Ya existe un usuario activo con ese mail");
        }

        Usuario usuarioCreado = usuario.toEntity();
        return UsuarioDto.toDto(usuarioRepository.save(usuarioCreado));
    }

    @Override
    public UsuarioDto findById(Long id) {
        Usuario usuario = usuarioRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado o eliminado"));
        return UsuarioDto.toDto(usuario);
    }

    @Override
    public UsuarioDto findByMail(String mail) {
        Usuario usuario = usuarioRepository.findByMailAndEliminadoFalse(mail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado o eliminado"));
        return UsuarioDto.toDto(usuario);
    }

    @Override
    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAllByEliminadoFalse().stream()
                .map(UsuarioDto::toDto)
                .toList();
    }

    @Override
    @Transactional
    public UsuarioDto update(UsuarioEdit usuarioEdit, Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con id " + idUsuario));

        // Si el mail cambia, validar que no esté usado por otro usuario activo (excluyendo el actual)
        if (usuarioEdit.mail() != null && !usuario.getMail().equalsIgnoreCase(usuarioEdit.mail()) &&
                usuarioRepository.existsByMailAndEliminadoFalseAndIdNot(usuarioEdit.mail(), idUsuario)) {
            throw new IllegalArgumentException("Ya existe otro usuario activo con ese mail");
        }

        usuarioEdit.applyTo(usuario);
        usuario = usuarioRepository.save(usuario);
        return UsuarioDto.toDto(usuario);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con id " + id));
        usuario.setEliminado(true);
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public UsuarioDto activate(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        if (!usuario.getEliminado()) {
            throw new IllegalArgumentException("El usuario ya está activo");
        }
        usuario.setEliminado(false);
        usuario = usuarioRepository.save(usuario);
        return UsuarioDto.toDto(usuario);
    }
}