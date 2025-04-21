package com.proyecto.contabilidad.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        String jwtToken = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("Cookie recibida: " + cookie.getName() + "=" + cookie.getValue());
                if ("jwt".equals(cookie.getName())) {
                    jwtToken = cookie.getValue();
                    break;
                }
            }
        } else {
            System.out.println(" No se recibieron cookies");
        }

        if (jwtToken != null) {
            boolean isValid = jwtUtil.validateToken(jwtToken);
            System.out.println("🔍 ¿Token válido? " + isValid);
            if (isValid) {
                String username = jwtUtil.extractUsername(jwtToken);
                System.out.println("👤 Usuario autenticado: " + username);
                request.setAttribute("username", username);
                filterChain.doFilter(request, response);
                return;
            } else {
                System.out.println(" Token inválido. Falló la validación.");
            }
        } else {
            System.out.println(" No se encontró la cookie 'jwt'");
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o ausente");
    }
    }

