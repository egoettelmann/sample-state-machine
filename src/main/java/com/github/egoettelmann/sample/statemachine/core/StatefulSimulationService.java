package com.github.egoettelmann.sample.statemachine.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;

public interface StatefulSimulationService {

    JsonNode reset();

    JsonNode current();

    JsonPatch next();

}
