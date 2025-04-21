package com.proyecto.contabilidad.mapper;

import com.proyecto.contabilidad.dto.CuentaContableResponseDTO;
import com.proyecto.contabilidad.model.CuentaContable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CuentaContableMapper {

    @Mapping(source = "usuario.firstname", target = "firstname")
    @Mapping(source = "usuario.lastname", target = "lastname")
    CuentaContableResponseDTO toDto(CuentaContable cuenta);
}
