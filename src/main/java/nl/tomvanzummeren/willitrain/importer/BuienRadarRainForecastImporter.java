package nl.tomvanzummeren.willitrain.importer;

import nl.tomvanzummeren.willitrain.RainSnapshot;
import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import nl.tomvanzummeren.willitrain.forecast.RainForecast;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import org.apache.sanselan.ImageReadException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static nl.tomvanzummeren.willitrain.forecast.PixelCoordinates.*;

/**
 * Imports rain forecast images from an external source and stores them in our local
 * {@link nl.tomvanzummeren.willitrain.forecast.RainForecast}.
 *
 * @author Tom van Zummeren
 */
@Component
public class BuienRadarRainForecastImporter {

    private static final int IMAGE_RANGE_MINIMUM_Y = 12;

    private static final int IMAGE_RANGE_MAXIMUM_Y = 500;

    private final RainForecast rainForecast;

    private final BuienradarImageLoader imageLoader;

    /**
     * Constructs a new {@code RainForecastImporter}.
     *
     * @param rainForecast to store new imported forecast snapshots
     * @param imageLoader  to load a forecast image from an external source
     */
    @Autowired
    public BuienRadarRainForecastImporter(RainForecast rainForecast, BuienradarImageLoader imageLoader) {
        this.rainForecast = rainForecast;
        this.imageLoader = imageLoader;
    }

    /**
     * Imports a forecast image for the given time in the future and stores the image in our local {@link RainForecast}.
     *
     * @param timeInFuture some time in the close future
     * @throws IOException        when unable to download the image
     * @throws ImageReadException when image is in an unsupported or illegal format
     */
    public void importForTimeInFuture(DateTime timeInFuture) throws IOException, ImageReadException {
        Resource forecastImageResource = imageLoader.loadRainForecastImage(timeInFuture);
        BufferedImage forecastImage = ImageIO.read(forecastImageResource.getInputStream());

        RainSnapshot rainSnapshot = rainForecast.forRainSnapshot(timeInFuture);

        for (int y = 0; y < forecastImage.getHeight(); y++) {
            for (int x = 0; x < forecastImage.getWidth(); x++) {
                if (indicatesRain(forecastImage, forPixel(x, y))) {
                    rainSnapshot.storeRainIntensity(forPixel(x, y), RainIntensity.RAIN);
                }
            }
        }
    }

    /**
     * Determines whether a pixel y-coordinate is within range. If outisde of the range, the pixel probably is part
     * of the labels on the top and bottom of the image.
     *
     * @param coordinates coordinates of the pixel
     * @return {@code true} if pixel is within range, {@code false} if not
     */
    private boolean withinRange(PixelCoordinates coordinates) {
        return coordinates.getY() >= IMAGE_RANGE_MINIMUM_Y && coordinates.getY() <= IMAGE_RANGE_MAXIMUM_Y;
    }

    /**
     * Determines whether a pixel in the given forecast image indicates rain.
     *
     * @param forecastImage    forecast image
     * @param coordinates coordinates of the pixel
     * @return {@code true} if the pixel indicates rain, {@code false} if not
     */
    private boolean indicatesRain(BufferedImage forecastImage, PixelCoordinates coordinates) {
        if (!withinRange(coordinates)) {
            return false;
        }
        int rgb = forecastImage.getRGB(coordinates.getX(), coordinates.getY());
        Color color = new Color(rgb);
        return !color.equals(Color.BLACK);
    }

}
