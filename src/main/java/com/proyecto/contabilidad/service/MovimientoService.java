package com.proyecto.contabilidad.service;

import com.proyecto.contabilidad.model.Movimiento;
import com.proyecto.contabilidad.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository repo;

    public Movimiento guardar(Movimiento movimiento) {
        return repo.save(movimiento);
    }

    public List<Movimiento> listar() {
        return repo.findAll();
    }

    public Movimiento buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Movimiento actualizar(Integer id, Movimiento actualizado) {
        Movimiento m = buscarPorId(id);
        if (m != null) {
            m.setFechaMovimiento(actualizado.getFechaMovimiento());
            m.setIdCuentaDebe(actualizado.getIdCuentaDebe());
            m.setIdCuentaHaber(actualizado.getIdCuentaHaber());
            m.setMonto(actualizado.getMonto());
            m.setDescripcion(actualizado.getDescripcion());
            return repo.save(m);
        }
        return null;
    }

}