package com.proyecto.contabilidad.controller;

import com.proyecto.contabilidad.dto.CuentaContableRequestDTO;
import com.proyecto.contabilidad.dto.CuentaContableResponseDTO;
import com.proyecto.contabilidad.model.Usuario;
import com.proyecto.contabilidad.repository.UsuarioRepository;
import com.proyecto.contabilidad.security.JwtUtil;
import com.proyecto.contabilidad.service.CuentaContableService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Arrays;
import java.util.List;

@Tag(name = "Cuenta Contable", description = "Operaciones relacionadas con las cuentas contables")
@RestController
@RequestMapping("/api/cuentas-contables")
public class CuentaContableController {

    @Autowired
    private CuentaContableService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(
            summary = "Crear cuenta contable",
            description = "Crea una nueva cuenta contable asociada al usuario autenticado",
            tags = {"Cuenta Contable"},
            requestBody = @RequestBody(
                    description = "Datos de la cuenta contable a crear",
                    required = true
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta contable creada exitosamente"),
            @ApiResponse(responseCode = "401", description = "Token inválido o expirado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PostMapping
    public ResponseEntity<CuentaContableResponseDTO> crearCuenta(
            @org.springframework.web.bind.annotation.RequestBody CuentaContableRequestDTO dto,
            HttpServletRequest request) {

        String token = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("jwt"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new RuntimeException("JWT no encontrado en cookies"));

        if (!jwtUtil.validateToken(token)) {
            throw new RuntimeException("Token inválido o expirado");
        }

        String username = jwtUtil.extractUsername(token);
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CuentaContableResponseDTO response = service.guardar(dto, usuario);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Actualizar cuenta contable",
            description = "Actualiza una cuenta contable existente por su ID",
            tags = {"Cuenta Contable"},
            requestBody = @RequestBody(
                    description = "Datos actualizados de la cuenta contable",
                    required = true
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CuentaContableResponseDTO> actualizarCuenta(
            @Parameter(description = "ID de la cuenta contable") @PathVariable Integer id,
            @org.springframework.web.bind.annotation.RequestBody CuentaContableRequestDTO dto) {

        CuentaContableResponseDTO response = service.actualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Listar cuentas contables",
            description = "Obtiene una lista de todas las cuentas contables registradas",
            tags = {"Cuenta Contable"}
    )
    @ApiResponse(responseCode = "200", description = "Lista de cuentas contables obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<CuentaContableResponseDTO>> listarCuentas() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Obtener cuenta contable",
            description = "Busca una cuenta contable por su ID",
            tags = {"Cuenta Contable"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta encontrada"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CuentaContableResponseDTO> obtenerCuenta(
            @Parameter(description = "ID de la cuenta contable") @PathVariable Integer id) {

        CuentaContableResponseDTO cuenta = service.buscarPorId(id);
        return cuenta != null ? ResponseEntity.ok(cuenta) : ResponseEntity.notFound().build();
    }
}
