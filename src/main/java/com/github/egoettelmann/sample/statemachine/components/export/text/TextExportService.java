package com.github.egoettelmann.sample.statemachine.components.export.text;

import com.github.egoettelmann.sample.statemachine.config.AppProperties;
import com.github.egoettelmann.sample.statemachine.core.ExportService;
import com.github.egoettelmann.sample.statemachine.core.FileUtils;
import com.github.egoettelmann.sample.statemachine.core.dtos.CellColor;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Coordinates;
import com.github.egoettelmann.sample.statemachine.core.dtos.grid.Grid;
import com.github.egoettelmann.sample.statemachine.core.exceptions.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@Profile("export-as-txt")
class TextExportService implements ExportService {

    /**
     * Text constants
     */
    private static final String TEXT_WHITE = " \u25A1 ";
    private static final String TEXT_BLACK = " \u25A0 ";
    private static final String TEXT_LEFT = " \u2039 ";
    private static final String TEXT_RIGHT = " \u203A ";
    private static final String TEXT_UP = " \u2303 ";
    private static final String TEXT_DOWN = " \u2304 ";

    /**
     * The app properties
     */
    private final AppProperties appProperties;

    /**
     * Instantiates the text export service.
     *
     * @param appProperties the app properties
     */
    @Autowired
    public TextExportService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    /**
     * Export as textual file.
     *
     * @param gridState the grid to export
     */
    @Override
    public void export(Grid<CellColor> gridState) {
        File file = FileUtils.generateFile(appProperties.getOutputPath(), "txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            write(writer, gridState);
            writer.close();
        } catch (IOException e) {
            log.error("Impossible to export as text: ", e);
            throw new AppException("Error while writing text file", e);
        }
        log.info("Created file '{}'", file.getPath());
    }

    /**
     * Write the grid to the writer.
     *
     * @param writer    the writer
     * @param gridState the grid state
     * @throws IOException write error
     */
    private void write(BufferedWriter writer, Grid<CellColor> gridState) throws IOException {
        Map<Coordinates, CellColor> cells = gridState.getCells();
        Pair<Coordinates, Coordinates> gridLimits = gridState.getGridLimits();

        for (Long y = gridLimits.getFirst().getY(); y >= gridLimits.getSecond().getY(); y--) {
            writer.newLine();

            for (Long x = gridLimits.getFirst().getX(); x <= gridLimits.getSecond().getX(); x++) {
                log.debug("Writing to coordinates ({},{})", x, y);
                Coordinates position = new Coordinates(x, y);

                if (position.equals(gridState.getCurrentPosition())) {
                    writeCurrent(writer, gridState.getCurrentOrientation());
                } else {
                    writeCell(writer, cells.get(position));
                }
            }
        }

        writer.newLine();
    }

    /**
     * Write a cell to the writer.
     *
     * @param writer      the writer
     * @param currentCell the cell to write
     * @throws IOException write error
     */
    private void writeCell(BufferedWriter writer, CellColor currentCell) throws IOException {
        String color = TEXT_WHITE;
        if (currentCell != null) {
            color = CellColor.WHITE.equals(currentCell) ? TEXT_WHITE : TEXT_BLACK;
        }
        log.debug("Writing cell with color: '{}'", color);
        writer.append(color);
    }

    /**
     * Write current position to the writer.
     *
     * @param writer             the writer
     * @param currentOrientation the current orientation
     * @throws IOException write error
     */
    private void writeCurrent(BufferedWriter writer, Long currentOrientation) throws IOException {
        String pointer = "";
        if (0 == currentOrientation) {
            pointer = TEXT_UP;
        }
        if (90 == currentOrientation) {
            pointer = TEXT_RIGHT;
        }
        if (180 == currentOrientation) {
            pointer = TEXT_DOWN;
        }
        if (270 == currentOrientation) {
            pointer = TEXT_LEFT;
        }
        log.debug("Writing current position with orientation: '{}'", pointer);
        writer.append(pointer);
    }

}
