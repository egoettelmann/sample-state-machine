package com.github.egoettelmann.sample.statemachine.config.rest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * Intercepts all exceptions thrown on controllers.
 * Exceptions are output as JSON with the problem-spring-web module.
 */
@ControllerAdvice
public class ControllerExceptionHandler implements ProblemHandling {

}
