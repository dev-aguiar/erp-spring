package com.devaguiar.erp.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto - ERP")
                        .description("Projeto de API REST para cadastro de clientes, produtos e pedidos.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("André Aguiar")
                                .url("https://github.com/dev-aguiar")
                                .email("andre.aguiar522@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub do Projeto")
                        .url("https://github.com/dev-aguiar"));
    }
}
