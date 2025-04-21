package com.proyecto.contabilidad.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "movimientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento {

    @Id
    @Column(name = "id_movimiento")
    private Integer idMovimiento;

    @Column(name = "fecha_movimiento")
    private LocalDate fechaMovimiento;

    @Column(name = "id_cuenta_debe")
    private Integer idCuentaDebe;

    @Column(name = "id_cuenta_haber")
    private Integer idCuentaHaber;

    @Column(name = "monto")
    private Integer monto;

    @Column(name = "descripcion")
    private String descripcion;
}