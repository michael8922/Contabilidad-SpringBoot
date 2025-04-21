package com.proyecto.contabilidad.interfaces;

import com.proyecto.contabilidad.dto.CuentaContableRequestDTO;
import com.proyecto.contabilidad.dto.CuentaContableResponseDTO;
import com.proyecto.contabilidad.model.Usuario;

import java.util.List;

public interface ICuentaContableService {

    CuentaContableResponseDTO guardar(CuentaContableRequestDTO dto, Usuario usuario);

    CuentaContableResponseDTO actualizar(Integer id, CuentaContableRequestDTO dto);

    List<CuentaContableResponseDTO> listar();

    CuentaContableResponseDTO buscarPorId(Integer id);
}
