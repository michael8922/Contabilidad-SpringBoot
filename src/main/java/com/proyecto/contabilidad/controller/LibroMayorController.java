package com.proyecto.contabilidad.controller;

import com.proyecto.contabilidad.interfaces.ILibroMayorService;
import com.proyecto.contabilidad.model.LibroMayor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Libro Mayor", description = "Operaciones relacionadas con el libro mayor contable")
@RestController
@RequestMapping("/api/libro-mayor")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class LibroMayorController {

    private final ILibroMayorService libroMayorService;

    @Operation(summary = "Guardar registro en libro mayor", description = "Crea un nuevo registro contable en el libro mayor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro guardado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos enviados en el cuerpo de la solicitud")
    })
    @PostMapping
    public LibroMayor save(@RequestBody LibroMayor libroMayor) {
        log.info("Guardando registro en libro mayor: {}", libroMayor);
        return libroMayorService.save(libroMayor);
    }

    @Operation(summary = "Listar libro mayor", description = "Obtiene todos los registros del libro mayor")
    @ApiResponse(responseCode = "200", description = "Lista de registros obtenida exitosamente")
    @GetMapping
    public List<LibroMayor> getAll() {
        log.info("Obteniendo todos los registros del libro mayor");
        return libroMayorService.getAll();
    }

    @Operation(summary = "Obtener registro por ID", description = "Busca un registro del libro mayor por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    @GetMapping("/{id}")
    public LibroMayor getById(
            @Parameter(description = "ID del registro del libro mayor") @PathVariable Integer id) {
        log.info("Buscando libro mayor por ID: {}", id);
        return libroMayorService.getById(id);
    }
}
