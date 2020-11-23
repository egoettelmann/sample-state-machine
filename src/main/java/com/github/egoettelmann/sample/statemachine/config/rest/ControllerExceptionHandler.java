package com.github.egoettelmann.sample.statemachine.config.rest;

import com.github.egoettelmann.sample.statemachine.core.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * Intercepts all exceptions thrown on controllers.
 * Exceptions are output as JSON with the problem-spring-web module.
 */
@ControllerAdvice
public class ControllerExceptionHandler implements ProblemHandling {

    @ExceptionHandler(AppException.class)
    @ResponseBody
    public ResponseEntity<Problem> handleAppException(AppException ex, NativeWebRequest request) {
        Problem problem = Problem.builder()
                .withStatus(Status.INTERNAL_SERVER_ERROR)
                .with("message", ex.getMessage())
                .build();
        return create(ex, problem, request);
    }

}
