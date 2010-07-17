package nl.tomvanzummeren.willitrain.forecast;

import org.joda.time.DateTime;

/**
 * Provides access to the rain forecast data store. Because the rain forecast is stored based on pixels, clients need to
 * specify locations as {@link PixelCoordinates}s. See
 * {@link nl.tomvanzummeren.willitrain.notifier.GeoLocationTranslator} to translate geo locations to pixel coordinates.
 *
 * @author Tom van Zummeren
 */
public interface RainForecast {

    RainSnapshot forRainSnapshot(DateTime time);
}
