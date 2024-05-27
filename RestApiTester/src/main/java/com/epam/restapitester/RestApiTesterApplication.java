package com.epam.restapitester;

import com.epam.restapitester.api.Genx;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "https://localhost:8020", description = "Default Server URL")})
public class RestApiTesterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiTesterApplication.class, args);



        Genx<String, String> genx = new Genx();
    }

    @Bean
    public RestClient restClient(){
        return RestClient.create();
    }

}
