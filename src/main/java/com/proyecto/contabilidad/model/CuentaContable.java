package com.proyecto.contabilidad.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cuentas_contables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaContable {

    @Id
    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @Column(name = "nombre_cuenta", nullable = false)
    private String nombreCuenta;

    @Column(name = "tipo_cuenta", nullable = false)
    private String tipoCuenta;

    @Column(name = "saldo_inicial", nullable = false)
    private Integer saldoInicial;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
