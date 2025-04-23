package com.proyecto.contabilidad.controller;

import com.proyecto.contabilidad.model.Movimiento;
import com.proyecto.contabilidad.service.MovimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin
@Tag(name = "Movimientos", description = "Operaciones para gestionar movimientos contables")
public class MovimientoController {

    @Autowired
    private MovimientoService service;

    @Operation(summary = "Listar todos los movimientos contables")
    @ApiResponse(responseCode = "200", description = "Lista de movimientos obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<Movimiento>> listar(
            @Parameter(hidden = true) HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        System.out.println("Listado de movimientos solicitado por: " + username);
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Obtener un movimiento por su ID")
    @ApiResponse(responseCode = "200", description = "Movimiento encontrado")
    @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtener(
            @Parameter(description = "ID del movimiento", required = true) @PathVariable Integer id,
            @Parameter(hidden = true) HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        System.out.println("Obtención de movimiento solicitada por: " + username);
        Movimiento m = service.buscarPorId(id);
        return m != null ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Crear un nuevo movimiento contable",
            requestBody = @RequestBody(description = "Movimiento a crear", required = true)
    )
    @ApiResponse(responseCode = "200", description = "Movimiento creado correctamente")
    @PostMapping
    public ResponseEntity<Movimiento> crear(
            @org.springframework.web.bind.annotation.RequestBody Movimiento movimiento,
            @Parameter(hidden = true) HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        System.out.println("Movimiento creado por: " + username);
        return ResponseEntity.ok(service.guardar(movimiento));
    }

    @Operation(
            summary = "Actualizar un movimiento existente",
            requestBody = @RequestBody(description = "Movimiento actualizado", required = true)
    )
    @ApiResponse(responseCode = "200", description = "Movimiento actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizar(
            @Parameter(description = "ID del movimiento", required = true) @PathVariable Integer id,
            @org.springframework.web.bind.annotation.RequestBody Movimiento m,
            @Parameter(hidden = true) HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        System.out.println("Movimiento actualizado por: " + username);
        Movimiento actualizado = service.actualizar(id, m);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }
}
