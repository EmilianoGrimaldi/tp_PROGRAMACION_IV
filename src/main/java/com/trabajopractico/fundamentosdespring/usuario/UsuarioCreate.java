package com.trabajopractico.fundamentosdespring.usuario;

import com.trabajopractico.fundamentosdespring.models.Rol;
import com.trabajopractico.fundamentosdespring.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record UsuarioCreate(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        String apellido,

        @NotBlank(message = "El mail es obligatorio")
        @Email(message = "El mail debe ser válido")
        String mail,

        @NotBlank(message = "El celular es obligatorio")
        String celular,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String contrasenia,

        @NotNull(message = "El rol es obligatorio")
        Rol rol
) {
    public Usuario toEntity(){
        return new Usuario(this.nombre, this.apellido, this.mail, this.celular, this.contrasenia, this.rol);
    }
}
