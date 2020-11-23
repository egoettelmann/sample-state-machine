package com.github.egoettelmann.sample.statemachine.components.simulation;

import com.github.egoettelmann.sample.statemachine.core.ExportService;
import com.github.egoettelmann.sample.statemachine.core.SimulationService;
import com.github.egoettelmann.sample.statemachine.core.dtos.CellColor;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Grid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class DefaultSimulationService implements SimulationService {

    private final ExportService exportService;

    @Autowired
    public DefaultSimulationService(ExportService exportService) {
        this.exportService = exportService;
    }

    @Override
    public Grid<CellColor> simulate(Integer moves) {
        GridStateMachine gridStateMachine = GridStateMachine.of(90L, CellColor.WHITE);

        // Executing the number of moves
        for (int i = 0; i < moves; i++) {
            gridStateMachine.next();
        }

        // Getting the grid
        Grid<CellColor> gridState = gridStateMachine.getState();

        // Exporting the grid
        exportService.export(gridState);

        return gridState;
    }

}
