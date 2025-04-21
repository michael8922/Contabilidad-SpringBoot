package com.proyecto.contabilidad.mapper;

import com.proyecto.contabilidad.dto.UsuarioRequestDTO;
import com.proyecto.contabilidad.dto.UsuarioResponseDTO;
import com.proyecto.contabilidad.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioRequestDTO dto);
    UsuarioResponseDTO toDto(Usuario usuario);
}
