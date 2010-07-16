package nl.tomvanzummeren.willitrain.forecast;

import org.springframework.stereotype.Repository;

/**
 * Uses a database to store and lookup the rain forecast.
 *
 * @author Tom van Zummeren
 */
@Repository
public class DatabaseRainForecast implements RainForecast {

    public RainIntensity lookupRainIntensity(PixelCoordinates pixelCoordinates, Time time) throws ForecastNotFoundException {
        throw new UnsupportedOperationException("implement");
    }

    public void storeRainIntensity(Time time, PixelCoordinates pixelCoordinates, RainIntensity rainIntensity) {
        throw new UnsupportedOperationException("implement");
    }
}
