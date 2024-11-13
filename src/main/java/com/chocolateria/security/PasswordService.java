package com.chocolateria.security;

import com.chocolateria.model.usuarios.RepositorioUsuario;
import com.chocolateria.model.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public boolean comprobarContrasenia(String email, String rawPassword) {
        // Aquí obtienes tu entidad Usuario en lugar de UserDetails
        Optional<Usuario> usuario = repositorioUsuario.findByEmail(email);
        // Compara la contraseña cruda con la encriptada en la base de datos
        return usuario.filter(value -> passwordEncoder.matches(rawPassword, value.getPassword())).isPresent();
    }
}
