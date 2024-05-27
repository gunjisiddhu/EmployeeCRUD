/*
package com.epam.restapitester.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.webmvc.ui.SwaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
public class OpenAPIConfig {

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
                .group("api") // Specify the Swagger group
                .pathsToMatch("/api/**") // Adjust the path based on your API
                .build();
    }

    @Bean
    public SwaggerUiConfigParameters swaggerUiConfigParameters() {
        return new SwaggerUiConfigParametersAdapter() {
            @Override
            public Boolean deepLinking() {
                return true;
            }
        };
    }

    @Bean
    public SwaggerConfig swaggerConfig() {
        return new SwaggerConfig() {
            @Override
            public String[] getIgnoredRoutes() {
                return new String[]{"/swagger-resources/*", "/swagger-ui/", "/v3/api-docs/*"};
            }
        };
    }
}
*/
