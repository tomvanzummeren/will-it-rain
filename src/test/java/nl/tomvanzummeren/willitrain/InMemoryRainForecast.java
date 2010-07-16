package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.ForecastNotFoundException;
import nl.tomvanzummeren.willitrain.forecast.PixelCoordinate;
import nl.tomvanzummeren.willitrain.forecast.RainForecast;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import nl.tomvanzummeren.willitrain.forecast.Time;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory implementation of a rain forecast. Used for testing purposes.
 *
 * @author Tom van Zummeren
 */
public class InMemoryRainForecast implements RainForecast {

    private Map<Time, WeatherSnapshot> timeToWeatherSnapshot = new HashMap<Time, WeatherSnapshot>();

    public RainIntensity lookupRainIntensity(PixelCoordinate pixelCoordinate, Time time) throws ForecastNotFoundException {
        WeatherSnapshot weatherSnapshot = timeToWeatherSnapshot.get(time);
        if (weatherSnapshot == null) {
            throw new ForecastNotFoundException();
        }
        RainIntensity intensity = weatherSnapshot.getRainIntensity(pixelCoordinate);
        return intensity == null ? RainIntensity.NONE : intensity;
    }

    public void storeRainIntensity(PixelCoordinate pixelCoordinate, Time time, RainIntensity rainIntensity) {
        WeatherSnapshot weatherSnapshot = getOrCreateWeatherSnapshot(time);
        weatherSnapshot.putRainIntensity(pixelCoordinate, rainIntensity);
    }

    private WeatherSnapshot getOrCreateWeatherSnapshot(Time time) {
        WeatherSnapshot weatherSnapshot = timeToWeatherSnapshot.get(time);
        if (weatherSnapshot == null) {
            weatherSnapshot = new WeatherSnapshot();
            timeToWeatherSnapshot.put(time, weatherSnapshot);
        }
        return weatherSnapshot;
    }

    private static class WeatherSnapshot {
        private Map<PixelCoordinate, RainIntensity> pixelCoordinateToRainIntensity = new HashMap<PixelCoordinate, RainIntensity>();

        public void putRainIntensity(PixelCoordinate pixelCoordinate, RainIntensity rainIntensity) {
            pixelCoordinateToRainIntensity.put(pixelCoordinate, rainIntensity);
        }

        public RainIntensity getRainIntensity(PixelCoordinate pixelCoordinate) {
            return pixelCoordinateToRainIntensity.get(pixelCoordinate);
        }
    }
}
