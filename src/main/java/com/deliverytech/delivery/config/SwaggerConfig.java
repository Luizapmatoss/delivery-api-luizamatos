package com.deliverytech.delivery.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI deliveryTechAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DeliveryTech API")
                        .description("API do sistema de Delivery para gerenciamento de clientes, restaurantes, produtos e pedidos.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe DeliveryTech")
                                .email("contato@deliverytech.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(
                        java.util.List.of(
                                new Server().url("http://localhost:8080").description("Ambiente de Desenvolvimento"),
                                new Server().url("https://deliverytech-api.com").description("Produção")
                        )
                );
    }
}
