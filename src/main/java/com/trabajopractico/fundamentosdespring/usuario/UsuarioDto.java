package com.trabajopractico.fundamentosdespring.usuario;

import com.trabajopractico.fundamentosdespring.models.Pedido;
import com.trabajopractico.fundamentosdespring.models.Rol;
import com.trabajopractico.fundamentosdespring.models.Usuario;

import java.util.List;

public record UsuarioDto(
        String nombre,
        String apellido,
        String mail,
        String celular,
        Rol rol,
        List<Pedido> pedidos
) {
    // ERROR: El tipo de retorno declarado es 'Usuario' (la entidad) pero el método
    // construye y retorna un 'UsuarioDto'. Esto causa un error de compilación.
    // El tipo de retorno debería ser 'UsuarioDto'.
    // ERROR: El nombre 'toDto' es engañoso: se trata de un método estático de conversión
    // (factory), no de una instancia. Una convención más clara sería 'fromEntity' o 'of'.
    public static UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(usuario.getNombre(), usuario.getApellido(), usuario.getMail(), usuario.getCelular(), usuario.getRol(), usuario.getPedidos());
    }
}
