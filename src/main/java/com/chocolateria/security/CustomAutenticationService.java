package com.chocolateria.security;
import com.chocolateria.model.usuarios.RepositorioUsuario;
import com.chocolateria.model.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

    @Service
    public class CustomAutenticationService implements UserDetailsService {


        @Autowired
        private RepositorioUsuario usuarioRepository;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            System.out.println("Iniciando autenticación para el email: " + email);
            Usuario usuario = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

            System.out.println("Usuario encontrado: " + usuario.getEmail());

            return new User(usuario.getUsername(), usuario.getPassword(), getAuthorities(usuario));
        }

        private Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
            return usuario.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                    .collect(Collectors.toList());
        }

    /*
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return repositorioUsuario.findByUserNameOrEmail(username, username);
        }*/
    }
