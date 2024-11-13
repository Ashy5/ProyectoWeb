package com.chocolateria.controller;

import com.chocolateria.model.usuarios.DtoRegistroUsuario;
import com.chocolateria.model.usuarios.RepositorioUsuario;
import com.chocolateria.model.usuarios.Usuario;
import com.chocolateria.model.usuarios.roles.RepositorioRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {

    @Autowired
    RepositorioUsuario repositorioUsuario;
    @Autowired
    RepositorioRoles rerpositorioRoles;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        // Añadir un nuevo objeto de usuario al modelo
        model.addAttribute("registroUsuario", new DtoRegistroUsuario("", "", "", "", ""));
        return "registro"; // Devuelve la plantilla Thymeleaf
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("registroUsuario") DtoRegistroUsuario registroUsuario,
                                   BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        // Validar campos (como si las contraseñas coinciden, los emails son iguales, etc.)
        validarDTO(registroUsuario, result);
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "registro";
        }

        Usuario usuario = new Usuario(registroUsuario, passwordEncoder);
        try {
            usuario.setRoles(rerpositorioRoles.findByNombre("ROLE_USER"));
            repositorioUsuario.save(usuario);
            String successfulMessage = "Registro exitoso.<br>Ahora puedes iniciar sesión.<br>" + usuario.getNombre();
            redirectAttributes.addFlashAttribute("successfulRegistroUsuario", successfulMessage);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "No se ha podido hacer el registro, por favor intente de nuevo";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/registro";
        } catch (Exception e) {
            String errorMessage = "A ocurrido un error, por favor intente de nuevo";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/registro";
        }

        // Redirigir a la página de éxito o de login

        return "redirect:/login";
    }

    private static void validarDTO(DtoRegistroUsuario registroUsuario, BindingResult result) {
        if (!registroUsuario.nombre().matches("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1 ]+$")) {
            result.rejectValue("nombre", "error.nombre", "El nombre solo puede contener letras y espacios, y no puede estar vacío");
        }
        if (!registroUsuario.apellido().matches("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1 ]+$")) {
            result.rejectValue("apellido", "error.apellido", "El apellido solo puede contener letras y espacios, y no puede estar vacío");
        }

        if (!registroUsuario.password().equals(registroUsuario.confirmPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword", "Las contraseñas no coinciden");
        }
    }
}

