package com.github.egoettelmann.sample.statemachine.core.dtos.grid;

import lombok.Getter;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Grid<T> {

    /**
     * The map of all cells by their coordinates: this map only holds already visited cells.
     */
    private final Map<Coordinates, T> cells = new HashMap<>();

    /**
     * The grid limits: extremities of the grid (upper left and lower right coordinates)
     */
    private final Pair<Coordinates, Coordinates> gridLimits;

    /**
     * The current position of the machine
     */
    private Coordinates currentPosition;

    /**
     * The current orientation of the machine (in degrees: 0 = up, 90 = right)
     */
    private Long currentOrientation;

    /**
     * Instantiates a new grid.
     *
     * @param currentPosition    the current position of the machine
     * @param currentOrientation the current orientation of the machine
     * @param cell               the cell for the current position
     */
    public Grid(Coordinates currentPosition, Long currentOrientation, T cell) {
        this.gridLimits = Pair.of(
                new Coordinates(0L, 0L),
                new Coordinates(0L, 0L)
        );
        setCurrent(currentPosition, currentOrientation, cell);
    }

    /**
     * Defines the current grid state.
     *
     * @param newCoordinates the coordinates of the new position
     * @param newOrientation the orientation of the new position
     * @param cell           the cell for the new position
     */
    public void setCurrent(Coordinates newCoordinates, Long newOrientation, T cell) {
        cells.put(newCoordinates, cell);
        currentOrientation = newOrientation;
        currentPosition = newCoordinates;
        readjustGridLimits();
    }

    /**
     * Readjusts the grid limits, based on the current position.
     */
    private void readjustGridLimits() {
        Coordinates upperLeft = gridLimits.getFirst();
        Coordinates lowerRight = gridLimits.getSecond();
        if (currentPosition.getX() < upperLeft.getX()) {
            upperLeft.setX(currentPosition.getX());
        }
        if (currentPosition.getY() > upperLeft.getY()) {
            upperLeft.setY(currentPosition.getY());
        }
        if (currentPosition.getX() > lowerRight.getX()) {
            lowerRight.setX(currentPosition.getX());
        }
        if (currentPosition.getY() < lowerRight.getY()) {
            lowerRight.setY(currentPosition.getY());
        }
    }

}
