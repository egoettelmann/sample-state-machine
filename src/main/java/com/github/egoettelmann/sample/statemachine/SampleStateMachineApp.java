package com.github.egoettelmann.sample.statemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class SampleStateMachineApp {

    public static void main(String[] args) {
        SpringApplication.run(SampleStateMachineApp.class, args);
    }

}
