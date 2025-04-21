package com.proyecto.contabilidad.dto;

import lombok.Data;

@Data
public class CuentaContableRequestDTO {

    private Integer idCuenta;
    private String nombreCuenta;
    private String tipoCuenta;
    private Integer saldoInicial;
}
