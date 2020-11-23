package com.github.egoettelmann.sample.statemachine.config.rest;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

@Configuration
public class SwaggerConfig {

    public SwaggerConfig() {
        SpringDocUtils.getConfig()
                .addRequestWrapperToIgnore(Pageable.class);
    }

    @Bean
    public OpenAPI sampleBankingApiSpecs() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sample State Machine App")
                        .description("Sample project: state machine")
                        .version("v0.0.1-SNAPSHOT")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Github Link")
                        .url("https://github.com/egoettelmann/sample-state-machine"))
                ;
    }

}
