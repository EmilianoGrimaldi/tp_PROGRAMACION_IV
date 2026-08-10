package com.trabajopractico.fundamentosdespring.usuario;

import com.trabajopractico.fundamentosdespring.models.Pedido;
import com.trabajopractico.fundamentosdespring.models.Rol;
import com.trabajopractico.fundamentosdespring.models.Usuario;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public record UsuarioDto(
        String nombre,
        String apellido,
        String mail,
        String celular,
        Rol rol,
        List<Pedido> pedidos
) {
    public static UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(usuario.getNombre(), usuario.getApellido(), usuario.getMail(), usuario.getCelular(), usuario.getRol(), usuario.getPedidos());
    }
}
