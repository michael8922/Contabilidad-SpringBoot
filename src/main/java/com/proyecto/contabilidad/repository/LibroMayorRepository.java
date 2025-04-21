package com.proyecto.contabilidad.repository;

import com.proyecto.contabilidad.model.LibroMayor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LibroMayorRepository extends JpaRepository<LibroMayor, Integer> {

}
