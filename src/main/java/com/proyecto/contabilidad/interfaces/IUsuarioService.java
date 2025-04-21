package com.proyecto.contabilidad.interfaces;

import com.proyecto.contabilidad.dto.UsuarioRequestDTO;
import com.proyecto.contabilidad.dto.UsuarioResponseDTO;

import java.util.List;

public interface IUsuarioService {

    UsuarioResponseDTO registrar(UsuarioRequestDTO dto);

    List<UsuarioResponseDTO> listar();

    UsuarioResponseDTO buscarPorId(Integer id);

    UsuarioResponseDTO actualizar(Integer id, UsuarioRequestDTO dto);

    void eliminar(Integer id);
}
