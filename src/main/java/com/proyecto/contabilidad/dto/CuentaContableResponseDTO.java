package com.proyecto.contabilidad.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CuentaContableResponseDTO {

    private Integer idCuenta;
    private String nombreCuenta;
    private String tipoCuenta;
    private Integer saldoInicial;
    private LocalDateTime fechaCreacion;

    private String firstname;
    private String lastname;
}
