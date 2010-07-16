package nl.tomvanzummeren.willitrain.forecast;

/**
 * Provides access to the rain forecast data store. Because the rain forecast is stored based on pixels, clients need to
 * specify locations as {@link PixelCoordinate}s. See
 * {@link nl.tomvanzummeren.willitrain.GeoLocationTranslator} to translate geo locations to pixel coordinates.
 *
 * @author Tom van Zummeren
 */
public interface RainForecast {

    /**
     * Looks up the rain location on a given geo location in a certain time.
     *
     * @param pixelCoordinate geo location to lookup
     * @param time        time in future
     * @return rain intensity for the given geo location and time
     * @throws ForecastNotFoundException when no data is available for the given time
     */
    RainIntensity lookupRainIntensity(PixelCoordinate pixelCoordinate, Time time) throws ForecastNotFoundException;

    void storeRainIntensity(PixelCoordinate pixelCoordinate, Time time, RainIntensity rainIntensity);
}
