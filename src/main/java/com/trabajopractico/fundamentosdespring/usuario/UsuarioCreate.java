package com.trabajopractico.fundamentosdespring.usuario;

import com.trabajopractico.fundamentosdespring.models.Rol;
import com.trabajopractico.fundamentosdespring.models.Usuario;


public record UsuarioCreate(
        String nombre,
        String apellido,
        String mail,
        String celular,
        String contrasenia,
        Rol rol
) {
    public Usuario toEntity(){
        return new Usuario(this.nombre, this.apellido, this.mail, this.celular, this.contrasenia, this.rol);
    }
}
