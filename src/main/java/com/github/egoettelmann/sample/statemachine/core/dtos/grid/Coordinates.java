package com.github.egoettelmann.sample.statemachine.core.dtos.grid;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {

    /**
     * The X position
     */
    private Long x;

    /**
     * The Y position
     */
    private Long y;

    @JsonValue
    public String asKey() {
        return x + ":" + y;
    }
}
