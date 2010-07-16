package nl.tomvanzummeren.willitrain.importer;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinate;
import nl.tomvanzummeren.willitrain.forecast.RainForecast;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import nl.tomvanzummeren.willitrain.forecast.Time;
import org.apache.sanselan.ImageReadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Imports rain forecast images from an external source and stores them in our local
 * {@link nl.tomvanzummeren.willitrain.forecast.RainForecast}.
 *
 * @author Tom van Zummeren
 */
@Component
public class RainForecastImporter {

    private RainForecast rainForecast;

    private RainForecastImageLoader imageLoader;

    @Autowired
    public RainForecastImporter(RainForecast rainForecast, RainForecastImageLoader imageLoader) {
        this.rainForecast = rainForecast;
        this.imageLoader = imageLoader;
    }

    public void importForTimeInFuture(Time timeInFuture) throws IOException, ImageReadException {
        Resource forecastImageResource = imageLoader.loadRainForecastImage(timeInFuture);

        BufferedImage forecastImage = ImageIO.read(forecastImageResource.getInputStream());

        for (int y = 0; y < forecastImage.getHeight(); y++) {
            for (int x = 0; x < forecastImage.getWidth(); x++) {
                int rgb = forecastImage.getRGB(x, y);
                Color color = new Color(rgb);
                if (!color.equals(Color.BLACK)) {
                    rainForecast.storeRainIntensity(new PixelCoordinate(x, y), timeInFuture, RainIntensity.RAIN);
                }
            }
        }
    }

}
