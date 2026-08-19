package com.trabajopractico.fundamentosdespring.usuario;


import com.trabajopractico.fundamentosdespring.models.Pedido;
import com.trabajopractico.fundamentosdespring.models.Rol;
import com.trabajopractico.fundamentosdespring.models.Usuario;

import java.util.List;

public record UsuarioResponseDTO(
        Long id,
        String nombre,
        String apellido,
        String mail,
        String celular,
        Rol rol,
        List<Pedido> pedidos
) {
    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getMail(),
                usuario.getCelular(),
                usuario.getRol(),
                usuario.getPedidos()
        );
    }

}
