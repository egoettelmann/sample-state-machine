package com.github.egoettelmann.sample.statemachine.components.export.image;

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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@Profile("export-as-img")
class ImageExportService implements ExportService {

    /**
     * The image resolution
     */
    private static final Integer RESOLUTION = 10;

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
    public ImageExportService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    /**
     * Export as textual file.
     *
     * @param gridState the grid to export
     */
    @Override
    public void export(Grid<CellColor> gridState) {
        File file = FileUtils.generateFile(appProperties.getOutputPath(), "png");
        try {
            // Computing image size
            Coordinates first = gridState.getGridLimits().getFirst();
            Coordinates second = gridState.getGridLimits().getSecond();
            int width = (1 + second.getX().intValue() - first.getX().intValue()) * RESOLUTION;
            int height = (1 + first.getY().intValue() - second.getY().intValue()) * RESOLUTION;
            log.debug("Drawing image {}x{}", width, height);

            // Creating image
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D image = bufferedImage.createGraphics();

            // Writing grid
            write(image, gridState);

            ImageIO.write(bufferedImage, "PNG", file);
        } catch (IOException e) {
            log.error("Impossible to export as image: ", e);
            throw new AppException("Error while writing image file", e);
        }
        log.info("Created file '{}'", file.getPath());
    }

    /**
     * Write the grid to the image.
     *
     * @param image     the image
     * @param gridState the grid state
     * @throws IOException write error
     */
    private void write(Graphics2D image, Grid<CellColor> gridState) throws IOException {
        Map<Coordinates, CellColor> cells = gridState.getCells();
        Pair<Coordinates, Coordinates> gridLimits = gridState.getGridLimits();

        // Image works with positive coordinates only: we need to shift all values
        int shiftX = Math.abs(gridLimits.getFirst().getX().intValue());
        int shiftY = Math.abs(gridLimits.getFirst().getY().intValue());

        for (Long y = gridLimits.getFirst().getY(); y >= gridLimits.getSecond().getY(); y--) {
            for (Long x = gridLimits.getFirst().getX(); x <= gridLimits.getSecond().getX(); x++) {
                log.debug("Writing to coordinates ({},{})", x, y);
                Coordinates position = new Coordinates(x, y);
                int imgX = (shiftX + x.intValue()) * RESOLUTION;
                int imgY = (shiftY - y.intValue()) * RESOLUTION; // 'Y' values are inverted on graphics
                writeCell(image, imgX, imgY, cells.get(position));
            }
        }

        // Adding current position
        Coordinates currentPosition = gridState.getCurrentPosition();
        int imgX = (shiftX + currentPosition.getX().intValue()) * RESOLUTION;
        int imgY = (shiftY - currentPosition.getY().intValue()) * RESOLUTION;
        writeCurrent(image, imgX, imgY, gridState.getCurrentOrientation());
    }

    /**
     * Write a cell to the image.
     *
     * @param image       the image
     * @param currentCell the cell to write
     * @throws IOException write error
     */
    private void writeCell(Graphics2D image, int x, int y, CellColor currentCell) throws IOException {
        Color color = Color.white;
        if (currentCell != null) {
            color = CellColor.WHITE.equals(currentCell) ? Color.white : Color.black;
        }
        log.debug("Writing cell to ({},{}) with color: {}", x, y, color);
        image.setPaint(color);
        image.fillRect(x, y, RESOLUTION, RESOLUTION);
    }

    /**
     * Write current position to the image.
     *
     * @param image              the image
     * @param currentOrientation the current orientation
     * @throws IOException write error
     */
    private void writeCurrent(Graphics2D image, int x, int y, Long currentOrientation) throws IOException {
        log.debug("Writing current position to ({},{}) with orientation: {}", x, y, currentOrientation);
        image.setPaint(Color.RED);
        if (0 == currentOrientation) {
            image.drawPolygon(new int[]{x, x + RESOLUTION, x + (RESOLUTION / 2)}, new int[]{y, y, y + RESOLUTION}, 3);
        }
        if (90 == currentOrientation) {
            image.drawPolygon(new int[]{x, x + RESOLUTION, x}, new int[]{y, y + (RESOLUTION / 2), y + RESOLUTION}, 3);
        }
        if (180 == currentOrientation) {
            image.drawPolygon(new int[]{x, x + RESOLUTION, x + (RESOLUTION / 2)}, new int[]{y + RESOLUTION, y + RESOLUTION, y}, 3);
        }
        if (270 == currentOrientation) {
            image.drawPolygon(new int[]{x, x + RESOLUTION, x + RESOLUTION}, new int[]{y + (RESOLUTION / 2), y + RESOLUTION, y}, 3);
        }
    }

}
