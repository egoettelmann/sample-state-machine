package com.github.egoettelmann.sample.statemachine.components.simulation;

import com.github.egoettelmann.sample.statemachine.core.StatefulSimulationService;
import com.github.egoettelmann.sample.statemachine.core.dtos.CellColor;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Grid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class DefaultStatefulSimulationService implements StatefulSimulationService {

    private GridStateMachine gridStateMachine;

    @Override
    public Grid<CellColor> nextState() {
        if (gridStateMachine == null) {
            gridStateMachine = GridStateMachine.of(90L, CellColor.WHITE);
            return gridStateMachine.getState();
        }

        // Executing the next move
        gridStateMachine.next();

        // Returning current state
        return gridStateMachine.getState();
    }

}
