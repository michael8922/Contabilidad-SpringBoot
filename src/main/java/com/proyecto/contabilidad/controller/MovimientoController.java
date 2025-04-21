package com.proyecto.contabilidad.controller;

import com.proyecto.contabilidad.model.Movimiento;
import com.proyecto.contabilidad.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin
public class MovimientoController {

    @Autowired
    private MovimientoService service;

    @GetMapping
    public ResponseEntity<List<Movimiento>> listar(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        System.out.println("Listado de movimientos solicitado por: " + username);
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtener(@PathVariable Integer id, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        System.out.println("Obtención de movimiento solicitada por: " + username);
        Movimiento m = service.buscarPorId(id);
        return m != null ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Movimiento> crear(@RequestBody Movimiento movimiento, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        System.out.println("Movimiento creado por: " + username);
        return ResponseEntity.ok(service.guardar(movimiento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizar(@PathVariable Integer id, @RequestBody Movimiento m, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        System.out.println("Movimiento actualizado por: " + username);
        Movimiento actualizado = service.actualizar(id, m);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

}
