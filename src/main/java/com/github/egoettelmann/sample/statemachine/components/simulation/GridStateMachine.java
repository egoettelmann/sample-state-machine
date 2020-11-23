package com.github.egoettelmann.sample.statemachine.components.simulation;

import com.github.egoettelmann.sample.statemachine.core.dtos.CellColor;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Coordinates;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Grid;

import java.util.Map;

/**
 * The type Grid state machine.
 */
class GridStateMachine {

    /**
     * The grid state
     */
    private final Grid<CellColor> gridState;

    /**
     * Instantiates the machine.
     *
     * @param gridState the initial grid state
     */
    private GridStateMachine(Grid<CellColor> gridState) {
        this.gridState = gridState;
    }

    /**
     * Builds a new grid state machine.
     *
     * @param initialOrientation the initial orientation
     * @param initialColor       the initial color
     * @return the grid state machine
     */
    public static GridStateMachine of(final Long initialOrientation, final CellColor initialColor) {
        Grid<CellColor> gridState = new Grid<>(
                new Coordinates(0L, 0L),
                initialOrientation,
                initialColor
        );
        return new GridStateMachine(gridState);
    }

    /**
     * Gets the current state.
     *
     * @return the state
     */
    public Grid<CellColor> getState() {
        return gridState;
    }

    /**
     * Performs the next move based on the computation rules.
     */
    public void next() {
        Map<Coordinates, CellColor> cells = gridState.getCells();
        CellColor currentCell = cells.get(gridState.getCurrentPosition());

        // Calculating the next orientation and the next coordinates
        Long nextOrientation = nextOrientation(gridState.getCurrentOrientation(), currentCell);
        Coordinates nextPosition = nextPosition(gridState.getCurrentPosition(), nextOrientation);

        // Switching the color of the current cell
        CellColor switchedCellColor = CellColor.BLACK.equals(currentCell) ? CellColor.WHITE : CellColor.BLACK;
        cells.put(gridState.getCurrentPosition(), switchedCellColor);

        // Retrieving next cell: if it does not exist we create a new one
        CellColor nextCellColor = cells.get(nextPosition);
        if (nextCellColor == null) {
            nextCellColor = CellColor.WHITE;
        }

        // Saving new state
        gridState.setCurrent(nextPosition, nextOrientation, nextCellColor);
    }

    /**
     * Computes the next orientation (based on the orientation and the color of the current cell).
     * We ensure that the orientation is always in range [0, 360).
     *
     * @param currentOrientation the current orientation
     * @param color              the current color
     * @return the next orientation
     */
    private static Long nextOrientation(Long currentOrientation, CellColor color) {
        if (CellColor.BLACK.equals(color)) {
            // BLACK -> 90° counter-clockwise
            return (currentOrientation + 270L) % 360;
        }
        // WHITE -> 90° clockwise
        return (currentOrientation + 90L) % 360;
    }

    /**
     * Computes the next coordinates (based on the position of the current cell and the next orientation).
     *
     * @param currentPosition the current position
     * @param orientation     the next orientation
     * @return the next position
     */
    private static Coordinates nextPosition(Coordinates currentPosition, Long orientation) {
        Long x = currentPosition.getX();
        Long y = currentPosition.getY();
        if (0 == orientation) {
            // 0° -> move up
            return new Coordinates(x, y + 1);
        }
        if (90 == orientation) {
            // 90° -> move right
            return new Coordinates(x + 1, y);
        }
        if (180 == orientation) {
            // 180° -> move down
            return new Coordinates(x, y - 1);
        }
        if (270 == orientation) {
            // 270° -> move left
            return new Coordinates(x - 1, y);
        }
        throw new IllegalArgumentException("Impossible to get next position from orientation: " + orientation);
    }

}
