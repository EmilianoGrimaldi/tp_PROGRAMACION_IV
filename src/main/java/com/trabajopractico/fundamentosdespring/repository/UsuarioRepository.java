package com.trabajopractico.fundamentosdespring.repository;

import com.trabajopractico.fundamentosdespring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
