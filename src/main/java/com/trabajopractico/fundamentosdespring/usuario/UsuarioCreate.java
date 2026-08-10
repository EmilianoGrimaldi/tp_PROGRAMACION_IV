package com.trabajopractico.fundamentosdespring.usuario;

import com.trabajopractico.fundamentosdespring.models.Pedido;
import com.trabajopractico.fundamentosdespring.models.Rol;
import com.trabajopractico.fundamentosdespring.models.Usuario;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public record UsuarioCreate(
        String nombre,
        String apellido,
        String mail,
        String celular,
        Rol rol,
        List<Pedido> pedidos
) {
    public Usuario toEntity(){
        return new Usuario(this.nombre, this.apellido, this.mail, this.celular, this.rol, this.pedidos);
    }
}
