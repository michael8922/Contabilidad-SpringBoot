package com.proyecto.contabilidad.controller;

import com.proyecto.contabilidad.dto.CuentaContableRequestDTO;
import com.proyecto.contabilidad.dto.CuentaContableResponseDTO;
import com.proyecto.contabilidad.model.Usuario;
import com.proyecto.contabilidad.repository.UsuarioRepository;
import com.proyecto.contabilidad.security.JwtUtil;
import com.proyecto.contabilidad.service.CuentaContableService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.Cookie;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas-contables")
public class CuentaContableController {

    @Autowired
    private CuentaContableService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Crea una nueva cuenta contable asociada al usuario autenticado.
     */
    @PostMapping
    public ResponseEntity<CuentaContableResponseDTO> crearCuenta(
            @RequestBody CuentaContableRequestDTO dto,
            HttpServletRequest request) {

        String token = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("jwt"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new RuntimeException("JWT no encontrado en cookies"));

        // Validar el token (opcional)
        if (!jwtUtil.validateToken(token)) {
            throw new RuntimeException("Token inválido o expirado");
        }

        // Extraer username desde el token
        String username = jwtUtil.extractUsername(token);

        // Buscar usuario por username
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CuentaContableResponseDTO response = service.guardar(dto, usuario);
        return ResponseEntity.ok(response);
    }

    /**
     * Actualizar una cuenta contable existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CuentaContableResponseDTO> actualizarCuenta(
            @PathVariable Integer id,
            @RequestBody CuentaContableRequestDTO dto) {

        CuentaContableResponseDTO response = service.actualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    /**
     * Listar todas las cuentas contables.
     */
    @GetMapping
    public ResponseEntity<List<CuentaContableResponseDTO>> listarCuentas() {
        return ResponseEntity.ok(service.listar());
    }

    /**
     * Buscar una cuenta contable por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CuentaContableResponseDTO> obtenerCuenta(@PathVariable Integer id) {
        CuentaContableResponseDTO cuenta = service.buscarPorId(id);
        if (cuenta != null) {
            return ResponseEntity.ok(cuenta);
        }
        return ResponseEntity.notFound().build();
    }
}
