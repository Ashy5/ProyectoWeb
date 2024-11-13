package com.chocolateria.model.usuarios;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;

public record DtoRegistroUsuario(
        String nombre,
        String apellido,
        String email,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String confirmPassword

) {
}