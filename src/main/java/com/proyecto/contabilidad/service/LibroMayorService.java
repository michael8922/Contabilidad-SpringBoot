package com.proyecto.contabilidad.service;

import com.proyecto.contabilidad.exception.ResourceNotFoundException;
import com.proyecto.contabilidad.interfaces.ILibroMayorService;
import com.proyecto.contabilidad.model.LibroMayor;
import com.proyecto.contabilidad.repository.LibroMayorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroMayorService implements ILibroMayorService {

    private final LibroMayorRepository libroMayorRepository;

    @Override
    public LibroMayor save(LibroMayor libroMayor) {
        return libroMayorRepository.save(libroMayor);
    }

    @Override
    public List<LibroMayor> getAll() {
        return libroMayorRepository.findAll();
    }

    @Override
    public LibroMayor getById(Integer id) {
        return libroMayorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LibroMayor con ID " + id + " no encontrado"));
    }

}