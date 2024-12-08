package com.chocolateria.model.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}