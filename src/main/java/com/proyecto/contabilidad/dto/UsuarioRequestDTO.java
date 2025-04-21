package com.proyecto.contabilidad.dto;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}