package com.github.egoettelmann.sample.statemachine.controllers;

import com.github.egoettelmann.sample.statemachine.core.SimulationService;
import com.github.egoettelmann.sample.statemachine.core.StatefulSimulationService;
import com.github.egoettelmann.sample.statemachine.core.dtos.CellColor;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulations")
public class SimulationsController {

    private final SimulationService simulationService;

    private final StatefulSimulationService statefulSimulationService;

    @Autowired
    public SimulationsController(
            SimulationService simulationService,
            StatefulSimulationService statefulSimulationService
    ) {
        this.simulationService = simulationService;
        this.statefulSimulationService = statefulSimulationService;
    }

    @GetMapping
    public Grid<CellColor> current() {
        return statefulSimulationService.nextState();
    }

    @PutMapping
    public Grid<CellColor> simulate(@RequestParam("moves") Integer moves) {
        return simulationService.simulate(moves);
    }

}
