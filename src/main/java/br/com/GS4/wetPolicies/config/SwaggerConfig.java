package br.com.GS4.wetPolicies.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI geraDocumentacao() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.description("WetPolicies Api");

        Contact contact = getContact();

        Info info = new Info().title("WetPolicies API")
                .version("0.1")
                .contact(contact)
                .description("Api que ranqueia deputados e seus posicionamentos legislativos");

        return new OpenAPI().info(info).servers(List.of(devServer));  }

    private static Contact getContact() {
        Contact contact = new Contact();

        contact.setEmail("rm97373@fiap.com.br");
        contact.setName("Raquel Calmon");

//        Contact contact2 = new Contact();
        contact.setEmail("rm97306@fiap.com.br");
        contact.setName("Lau Costa");

//        Contact contact3 = new Contact();
        contact.setEmail("rm97041@fiap.com.br");
        contact.setName("Johan Marzolla");

//        Contact contact4 = new Contact();
        contact.setEmail("rm96553@fiap.com.br");
        contact.setName("Felipe Seiji");

//        Contact contact5 = new Contact();
        contact.setEmail("rm96542@fiap.com.br");
        contact.setName("Gustavo Ballogh");
        return contact;
    }
}