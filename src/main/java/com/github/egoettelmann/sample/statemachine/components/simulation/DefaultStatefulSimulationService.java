package com.github.egoettelmann.sample.statemachine.components.simulation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.egoettelmann.sample.statemachine.core.StatefulSimulationService;
import com.github.egoettelmann.sample.statemachine.core.dtos.CellColor;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Grid;
import com.github.egoettelmann.sample.statemachine.core.exceptions.NotInitializedException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.diff.JsonDiff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class DefaultStatefulSimulationService implements StatefulSimulationService {

    private final ObjectMapper objectMapper;

    private GridStateMachine gridStateMachine;

    @Autowired
    public DefaultStatefulSimulationService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public JsonNode reset() {
        gridStateMachine = GridStateMachine.of(90L, CellColor.WHITE);
        return current();
    }

    @Override
    public JsonNode current() {
        if (gridStateMachine == null) {
            throw new NotInitializedException("State machine has not been initialized");
        }
        Grid<CellColor> currentState = gridStateMachine.getState();
        return objectMapper.valueToTree(currentState);
    }

    @Override
    public JsonPatch next() {
        if (gridStateMachine == null) {
            throw new NotInitializedException("State machine has not been initialized");
        }
        JsonNode previousState = current();
        gridStateMachine.next();
        JsonNode currentState = current();
        return JsonDiff.asJsonPatch(previousState, currentState);
    }

}
