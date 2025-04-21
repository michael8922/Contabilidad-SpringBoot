package com.proyecto.contabilidad.controller;

import com.proyecto.contabilidad.interfaces.ILibroMayorService;
import com.proyecto.contabilidad.model.LibroMayor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libro-mayor")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class LibroMayorController {

    private final ILibroMayorService libroMayorService;

    @PostMapping
    public LibroMayor save(@RequestBody LibroMayor libroMayor) {
        log.info("Guardando registro en libro mayor: {}", libroMayor);
        return libroMayorService.save(libroMayor);
    }

    @GetMapping
    public List<LibroMayor> getAll() {
        log.info("Obteniendo todos los registros del libro mayor");
        return libroMayorService.getAll();
    }

    @GetMapping("/{id}")
    public LibroMayor getById(@PathVariable Integer id) {
        log.info("Buscando libro mayor por ID: {}", id);
        return libroMayorService.getById(id);
    }


}
