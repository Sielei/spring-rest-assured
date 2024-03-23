package com.spra.springrestassured.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenApiConfig {

    @Bean
    OpenAPI openAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Jobs API")
                        .description("This is a simple API Application created to demonstrate the capabilities of Rest Assured for API Testing")
                        .version("1.0.0")
                        .contact(new Contact().name("Sielei Herman").email("hsielei@gmail.com"))
                );
    }
}
