package com.proyecto.contabilidad.repository;

import com.proyecto.contabilidad.model.CuentaContable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaContableRepository extends JpaRepository<CuentaContable, Integer> {

}