package com.github.egoettelmann.sample.statemachine.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.egoettelmann.sample.statemachine.core.SimulationService;
import com.github.egoettelmann.sample.statemachine.core.StatefulSimulationService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/state")
public class StatefulSimulationController {

    private final SimulationService simulationService;

    private final StatefulSimulationService statefulSimulationService;

    @Autowired
    public StatefulSimulationController(
            SimulationService simulationService,
            StatefulSimulationService statefulSimulationService
    ) {
        this.simulationService = simulationService;
        this.statefulSimulationService = statefulSimulationService;
    }

    @GetMapping
    public JsonNode current() {
        return statefulSimulationService.current();
    }

    @PutMapping
    public JsonPatch next() {
        return statefulSimulationService.next();
    }

    @DeleteMapping
    public JsonNode reset() {
        return statefulSimulationService.reset();
    }


}
