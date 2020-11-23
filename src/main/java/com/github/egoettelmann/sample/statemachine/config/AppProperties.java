package com.github.egoettelmann.sample.statemachine.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "state-machine-app")
public class AppProperties {

    private String outputPath;

}
