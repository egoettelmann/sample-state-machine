package com.github.egoettelmann.sample.statemachine.core;

import com.github.egoettelmann.sample.statemachine.core.dtos.CellColor;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Grid;

public interface ExportService {

    void export(Grid<CellColor> grid);

}
