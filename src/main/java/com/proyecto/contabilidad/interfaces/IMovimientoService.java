package com.proyecto.contabilidad.interfaces;

import com.proyecto.contabilidad.model.Movimiento;

import java.util.List;

public interface IMovimientoService {
    Movimiento guardar(Movimiento movimiento);
    List<Movimiento> listar();
    Movimiento buscarPorId(Integer id);
    Movimiento actualizar(Integer id, Movimiento movimiento);
}

