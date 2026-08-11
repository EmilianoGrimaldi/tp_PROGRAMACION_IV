package com.trabajopractico.fundamentosdespring.usuario;

import com.trabajopractico.fundamentosdespring.models.Pedido;
import com.trabajopractico.fundamentosdespring.models.Rol;
import com.trabajopractico.fundamentosdespring.models.Usuario;

import java.util.List;

public record UsuarioDto(
        Long id,
        String nombre,
        String apellido,
        String mail,
        String celular,
        Rol rol,
        List<Pedido> pedidos
) {
    public static UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getMail(), usuario.getCelular(), usuario.getRol(), usuario.getPedidos());
    }
}
