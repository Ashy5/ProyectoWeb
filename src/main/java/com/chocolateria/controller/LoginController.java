package com.chocolateria.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        // Verifica si el usuario está autenticado
        if (request.getUserPrincipal() != null) {
            return "redirect:index.html";
        }
        return "login"; // Devuelve la vista de login si no está autenticado
    }


    @GetMapping("/logout")
    public void logout(/*HttpServletRequest request*/) {
        /*if (request.getUserPrincipal() == null) {
            return "redirect:/login?logout=Primero+inicia+sesion";
        }else {
            return "redirect:/login?logout=Ha+cerrado+sesion";
        }*/
        // This method can be left empty as Spring Security handles the logout process
    }





}
