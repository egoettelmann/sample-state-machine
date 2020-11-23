package com.github.egoettelmann.sample.statemachine.components.simulation;

import com.github.egoettelmann.sample.statemachine.core.SimulationService;
import com.github.egoettelmann.sample.statemachine.core.dtos.CellColor;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Grid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class DefaultSimulationService implements SimulationService {

    @Override
    public Grid<CellColor> simulate(Integer moves) {
        GridStateMachine gridStateMachine = GridStateMachine.of(90L, CellColor.WHITE);

        for (int i = 0; i < moves; i++) {
            gridStateMachine.next();
        }

        return gridStateMachine.getState();
    }

}
