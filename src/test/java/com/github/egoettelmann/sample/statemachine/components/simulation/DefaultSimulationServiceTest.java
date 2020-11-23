package com.github.egoettelmann.sample.statemachine.components.simulation;

import com.github.egoettelmann.sample.statemachine.core.dtos.CellColor;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Coordinates;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Grid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test results are described visually in comments with:
 *  - 'x': black cell
 *  - 'o': white cell
 *  - '>'/'v'/'<'/'^': current position with orientation
 */
public class DefaultSimulationServiceTest {

    private final DefaultSimulationService simulationService = new DefaultSimulationService();

    private static final Coordinates COORDINATES_0 = new Coordinates(0L, 0L);
    private static final Coordinates COORDINATES_1 = new Coordinates(0L, -1L);
    private static final Coordinates COORDINATES_2 = new Coordinates(-1L, -1L);
    private static final Coordinates COORDINATES_3 = new Coordinates(-1L, 0L);
    private static final Coordinates COORDINATES_4 = new Coordinates(0L, 0L);
    private static final Coordinates COORDINATES_5 = new Coordinates(0L, 1L);

    /**
     * >
     */
    @Test
    public void testGridStateWithNoMoves() {
        Grid<CellColor> state = simulationService.simulate(0);

        Assertions.assertEquals(1, state.getCells().size(), "Wrong number of cells");
        Assertions.assertEquals(90L, state.getCurrentOrientation(), "Wrong current orientation");
        Assertions.assertEquals(COORDINATES_0, state.getCurrentPosition(), "Wrong current position");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_0), "Coordinates 0 not found");
        CellColor cell0 = state.getCells().get(COORDINATES_0);
        Assertions.assertEquals(CellColor.WHITE, cell0, "Wrong color for coordinates 0");
    }

    /**
     * x
     * v
     */
    @Test
    public void testGridStateWithOneMove() {
        Grid<CellColor> state = simulationService.simulate(1);

        Assertions.assertEquals(2, state.getCells().size(), "Wrong number of cells");
        Assertions.assertEquals(180L, state.getCurrentOrientation(), "Wrong current orientation");
        Assertions.assertEquals(COORDINATES_1, state.getCurrentPosition(), "Wrong current position");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_0), "Coordinates 0 not found");
        CellColor cell0 = state.getCells().get(COORDINATES_0);
        Assertions.assertEquals(CellColor.BLACK, cell0, "Wrong color for coordinates 0");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_1), "Coordinates 1 not found");
        CellColor cell1 = state.getCells().get(COORDINATES_1);
        Assertions.assertEquals(CellColor.WHITE, cell1, "Wrong color for coordinates 1");
    }

    /**
     * o x
     * < x
     */
    @Test
    public void testGridStateWithTwoMoves() {
        Grid<CellColor> state = simulationService.simulate(2);

        Assertions.assertEquals(3, state.getCells().size(), "Wrong number of cells");
        Assertions.assertEquals(270L, state.getCurrentOrientation(), "Wrong current orientation");
        Assertions.assertEquals(COORDINATES_2, state.getCurrentPosition(), "Wrong current position");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_0), "Coordinates 0 not found");
        CellColor cell0 = state.getCells().get(COORDINATES_0);
        Assertions.assertEquals(CellColor.BLACK, cell0, "Wrong color for coordinates 0");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_1), "Coordinates 1 not found");
        CellColor cell1 = state.getCells().get(COORDINATES_1);
        Assertions.assertEquals(CellColor.BLACK, cell1, "Wrong color for coordinates 1");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_2), "Coordinates 2 not found");
        CellColor cell2 = state.getCells().get(COORDINATES_2);
        Assertions.assertEquals(CellColor.WHITE, cell2, "Wrong color for coordinates 2");
    }

    /**
     * ^ x
     * x x
     */
    @Test
    public void testGridStateWithThreeMoves() {
        Grid<CellColor> state = simulationService.simulate(3);

        Assertions.assertEquals(4, state.getCells().size(), "Wrong number of cells");
        Assertions.assertEquals(0L, state.getCurrentOrientation(), "Wrong current orientation");
        Assertions.assertEquals(COORDINATES_3, state.getCurrentPosition(), "Wrong current position");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_0), "Coordinates 0 not found");
        CellColor cell0 = state.getCells().get(COORDINATES_0);
        Assertions.assertEquals(CellColor.BLACK, cell0, "Wrong color for coordinates 0");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_1), "Coordinates 1 not found");
        CellColor cell1 = state.getCells().get(COORDINATES_1);
        Assertions.assertEquals(CellColor.BLACK, cell1, "Wrong color for coordinates 1");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_2), "Coordinates 2 not found");
        CellColor cell2 = state.getCells().get(COORDINATES_2);
        Assertions.assertEquals(CellColor.BLACK, cell2, "Wrong color for coordinates 2");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_3), "Coordinates 3 not found");
        CellColor cell3 = state.getCells().get(COORDINATES_3);
        Assertions.assertEquals(CellColor.WHITE, cell3, "Wrong color for coordinates 3");
    }

    /**
     * x >
     * x x
     */
    @Test
    public void testGridStateWithFourMoves() {
        Grid<CellColor> state = simulationService.simulate(4);

        Assertions.assertEquals(4, state.getCells().size(), "Wrong number of cells");
        Assertions.assertEquals(90L, state.getCurrentOrientation(), "Wrong current orientation");
        Assertions.assertEquals(COORDINATES_4, state.getCurrentPosition(), "Wrong current position");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_0), "Coordinates 0 not found");
        CellColor cell0 = state.getCells().get(COORDINATES_0);
        Assertions.assertEquals(CellColor.BLACK, cell0, "Wrong color for coordinates 0");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_1), "Coordinates 1 not found");
        CellColor cell1 = state.getCells().get(COORDINATES_1);
        Assertions.assertEquals(CellColor.BLACK, cell1, "Wrong color for coordinates 1");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_2), "Coordinates 2 not found");
        CellColor cell2 = state.getCells().get(COORDINATES_2);
        Assertions.assertEquals(CellColor.BLACK, cell2, "Wrong color for coordinates 2");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_3), "Coordinates 3 not found");
        CellColor cell3 = state.getCells().get(COORDINATES_3);
        Assertions.assertEquals(CellColor.BLACK, cell3, "Wrong color for coordinates 3");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_4), "Coordinates 4 not found");
        CellColor cell4 = state.getCells().get(COORDINATES_4);
        Assertions.assertEquals(CellColor.BLACK, cell4, "Wrong color for coordinates 4");
    }

    /**
     * o ^
     * x o
     * x x
     */
    @Test
    public void testGridStateWithFiveMoves() {
        Grid<CellColor> state = simulationService.simulate(5);

        Assertions.assertEquals(5, state.getCells().size(), "Wrong number of cells");
        Assertions.assertEquals(0L, state.getCurrentOrientation(), "Wrong current orientation");
        Assertions.assertEquals(COORDINATES_5, state.getCurrentPosition(), "Wrong current position");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_0), "Coordinates 0 not found");
        CellColor cell0 = state.getCells().get(COORDINATES_0);
        Assertions.assertEquals(CellColor.WHITE, cell0, "Wrong color for coordinates 0");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_1), "Coordinates 1 not found");
        CellColor cell1 = state.getCells().get(COORDINATES_1);
        Assertions.assertEquals(CellColor.BLACK, cell1, "Wrong color for coordinates 1");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_2), "Coordinates 2 not found");
        CellColor cell2 = state.getCells().get(COORDINATES_2);
        Assertions.assertEquals(CellColor.BLACK, cell2, "Wrong color for coordinates 2");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_3), "Coordinates 3 not found");
        CellColor cell3 = state.getCells().get(COORDINATES_3);
        Assertions.assertEquals(CellColor.BLACK, cell3, "Wrong color for coordinates 3");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_4), "Coordinates 4 not found");
        CellColor cell4 = state.getCells().get(COORDINATES_4);
        Assertions.assertEquals(CellColor.WHITE, cell4, "Wrong color for coordinates 4");

        Assertions.assertTrue(state.getCells().containsKey(COORDINATES_5), "Coordinates 5 not found");
        CellColor cell5 = state.getCells().get(COORDINATES_5);
        Assertions.assertEquals(CellColor.WHITE, cell5, "Wrong color for coordinates 5");
    }

}
