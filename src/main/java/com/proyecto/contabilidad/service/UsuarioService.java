package com.proyecto.contabilidad.service;

import com.proyecto.contabilidad.dto.UsuarioRequestDTO;
import com.proyecto.contabilidad.dto.UsuarioResponseDTO;
import com.proyecto.contabilidad.interfaces.IUsuarioService;
import com.proyecto.contabilidad.mapper.UsuarioMapper;
import com.proyecto.contabilidad.model.Rol;
import com.proyecto.contabilidad.model.Usuario;
import com.proyecto.contabilidad.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private UsuarioMapper mapper;

    public UsuarioResponseDTO registrar(UsuarioRequestDTO dto) {
        Usuario nuevo = mapper.toEntity(dto);
        nuevo.setRol(Rol.USER); // o Rol.ADMIN si lo decides tú
        return mapper.toDto(repo.save(nuevo));
    }

    public List<UsuarioResponseDTO> listar() {
        return repo.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(Integer id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    public UsuarioResponseDTO actualizar(Integer id, UsuarioRequestDTO dto) {
        return repo.findById(id).map(usuario -> {
            usuario.setFirstname(dto.getFirstname());
            usuario.setLastname(dto.getLastname());
            usuario.setUsername(dto.getUsername());
            usuario.setPassword(dto.getPassword());
            return mapper.toDto(repo.save(usuario));
        }).orElse(null);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}

