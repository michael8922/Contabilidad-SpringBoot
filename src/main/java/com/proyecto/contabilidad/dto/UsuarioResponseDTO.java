package com.proyecto.contabilidad.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String rol;
}
