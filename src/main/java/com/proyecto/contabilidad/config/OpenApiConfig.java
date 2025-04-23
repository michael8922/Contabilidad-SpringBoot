package com.proyecto.contabilidad.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
public class OpenApiConfig {

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    @ConditionalOnProperty(name = "springdoc.api-docs.enabled", havingValue = "true", matchIfMissing = true)
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API: " + appName)
                        .version("1.0")
                        .description("Documentación de la API para el sistema contable"))
                .addServersItem(new Server()
                        .url("http://localhost:" + serverPort)
                        .description("Servidor Local"))
                .addServersItem(new Server()
                        .url("https://10.0.0.150")
                        .description("Servidor de Desarrollo"));
    }
}
