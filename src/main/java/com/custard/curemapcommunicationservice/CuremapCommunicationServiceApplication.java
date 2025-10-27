package com.custard.curemapcommunicationservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(
        info = @Info(
                title = "Communication service REST-API Documentation",
                description = "CureMap Communication Microservice REST-API Documentation, contains the apis for managing POSTs and Comments; This service also uses SocketIO for realtime time communication on PORT: 9092",
                version = "v1",
                contact = @Contact(
                        name = "Winston",
                        email = "Winstyngyen@gmail.com"
                ),
                license = @License(
                        name = "MIT"
                )
        )
)
public class CuremapCommunicationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuremapCommunicationServiceApplication.class, args);
    }
}
