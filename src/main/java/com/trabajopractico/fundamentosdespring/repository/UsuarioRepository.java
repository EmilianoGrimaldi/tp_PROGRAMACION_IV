package com.trabajopractico.fundamentosdespring.repository;

import com.trabajopractico.fundamentosdespring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByMail(String mail);
    Optional<Usuario> findByIdAndEliminadoFalse(Long id);
    Optional<Usuario> findByMailAndEliminadoFalse(String mail);
    List<Usuario> findAllByEliminadoFalse();
    boolean existsByMailAndEliminadoFalse(String mail);
    boolean existsByMailAndEliminadoFalseAndIdNot(String mail, Long id);
}
