package com.trabajopractico.fundamentosdespring.usuario;

import com.trabajopractico.fundamentosdespring.models.Rol;
import com.trabajopractico.fundamentosdespring.models.Usuario;

public record UsuarioEdit(
        String nombre,
        String apellido,
        String mail,
        String celular,
        String contrasenia,
        Rol rol
) {
    public void applyTo(Usuario usuario){
        if (this.nombre != null){
            usuario.setNombre(this.nombre);
        }
        if (this.apellido != null){
            usuario.setApellido(this.apellido);
        }
        if (this.mail != null){
            usuario.setMail(this.mail);
        }
        if (this.celular != null){
            usuario.setCelular(this.celular);
        }
        if (this.rol != null){
            usuario.setRol(this.rol);
        }
        if (this.contrasenia != null){
            usuario.setContrasenia(this.contrasenia);
        }
    }
}
