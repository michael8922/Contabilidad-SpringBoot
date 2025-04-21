package com.proyecto.contabilidad.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "libro_mayor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroMayor {

    @Id
    @Column(name = "id_libro_mayor")
    private Integer idLibroMayor;

    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "saldo_anterior")
    private Integer saldoAnterior;

    @Column(name = "debe")
    private Integer debe;

    @Column(name = "haber")
    private Integer haber;

    @Column(name = "saldo_actual")
    private Integer saldoActual;
}
