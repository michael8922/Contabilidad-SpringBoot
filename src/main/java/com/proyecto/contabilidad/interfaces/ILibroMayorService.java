package com.proyecto.contabilidad.interfaces;

import com.proyecto.contabilidad.model.LibroMayor;

import java.util.List;

public interface ILibroMayorService {
    LibroMayor save(LibroMayor libroMayor);
    List<LibroMayor> getAll();
    LibroMayor getById(Integer id);

}