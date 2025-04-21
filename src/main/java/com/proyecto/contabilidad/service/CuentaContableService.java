package com.proyecto.contabilidad.service;

import com.proyecto.contabilidad.dto.CuentaContableRequestDTO;
import com.proyecto.contabilidad.dto.CuentaContableResponseDTO;
import com.proyecto.contabilidad.interfaces.ICuentaContableService;
import com.proyecto.contabilidad.mapper.CuentaContableMapper;
import com.proyecto.contabilidad.model.CuentaContable;
import com.proyecto.contabilidad.model.Usuario;
import com.proyecto.contabilidad.repository.CuentaContableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaContableService implements ICuentaContableService {

    @Autowired
    private CuentaContableRepository repo;

    @Autowired
    private CuentaContableMapper mapper;

    @Override
    public CuentaContableResponseDTO guardar(CuentaContableRequestDTO dto, Usuario usuario) {
        CuentaContable cuenta = CuentaContable.builder()
                .idCuenta(dto.getIdCuenta())
                .nombreCuenta(dto.getNombreCuenta())
                .tipoCuenta(dto.getTipoCuenta())
                .saldoInicial(dto.getSaldoInicial())
                .fechaCreacion(LocalDateTime.now())
                .usuario(usuario)
                .build();

        return mapper.toDto(repo.save(cuenta));
    }

    @Override
    public List<CuentaContableResponseDTO> listar() {
        return repo.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaContableResponseDTO buscarPorId(Integer id) {
        CuentaContable cuenta = repo.findById(id).orElse(null);
        return cuenta != null ? mapper.toDto(cuenta) : null;
    }

    @Override
    public CuentaContableResponseDTO actualizar(Integer id, CuentaContableRequestDTO dto) {
        CuentaContable cuenta = repo.findById(id).orElse(null);
        if (cuenta != null) {
            cuenta.setNombreCuenta(dto.getNombreCuenta());
            cuenta.setTipoCuenta(dto.getTipoCuenta());
            cuenta.setSaldoInicial(dto.getSaldoInicial());

            return mapper.toDto(repo.save(cuenta));
        }
        return null;
    }


}
