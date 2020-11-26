package com.github.egoettelmann.sample.statemachine.controllers;

import com.github.egoettelmann.sample.statemachine.core.SimulationService;
import com.github.egoettelmann.sample.statemachine.core.dtos.CellColor;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulations")
public class SimulationsController {

    private final SimulationService simulationService;

    @Autowired
    public SimulationsController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PutMapping
    public Grid<CellColor> simulate(@RequestParam("moves") Integer moves) {
        return simulationService.simulate(moves);
    }

}
