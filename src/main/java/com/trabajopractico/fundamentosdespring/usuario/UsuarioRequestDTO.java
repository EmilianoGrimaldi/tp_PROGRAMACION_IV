package com.trabajopractico.fundamentosdespring.usuario;

import com.trabajopractico.fundamentosdespring.models.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
        String nombre,

        @NotBlank(message = "El apellido no puede estar vacío")
        @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
        String apellido,

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "Formato de email inválido")
        String mail,

        @NotBlank(message = "El celular no puede estar vacío")
        @Pattern(
                regexp = "^\\+?[0-9]{10,15}$",
                message = "El formato del celular es inválido. Debe contener entre 10 y 15 dígitos."
        )
        String celular,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String contrasenia,

        @NotBlank(message = "El rol no puede estar vacío")
        Rol rol
) { }
