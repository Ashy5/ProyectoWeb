package com.chocolateria.model.usuarios.roles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RepositorioRoles extends JpaRepository<Rol, Long> {
    Set<Rol> findByNombre(String nombre);
}
