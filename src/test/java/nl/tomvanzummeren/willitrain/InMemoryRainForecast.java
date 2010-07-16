package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.ForecastNotFoundException;
import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
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

    public RainIntensity lookupRainIntensity(PixelCoordinates pixelCoordinates, Time time) throws ForecastNotFoundException {
        WeatherSnapshot weatherSnapshot = timeToWeatherSnapshot.get(time);
        if (weatherSnapshot == null) {
            throw new ForecastNotFoundException();
        }
        RainIntensity intensity = weatherSnapshot.getRainIntensity(pixelCoordinates);
        return intensity == null ? RainIntensity.NONE : intensity;
    }

    public void storeRainIntensity(Time time, PixelCoordinates pixelCoordinates, RainIntensity rainIntensity) {
        WeatherSnapshot weatherSnapshot = getOrCreateWeatherSnapshot(time);
        weatherSnapshot.putRainIntensity(pixelCoordinates, rainIntensity);
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
        private Map<PixelCoordinates, RainIntensity> pixelCoordinateToRainIntensity = new HashMap<PixelCoordinates, RainIntensity>();

        public void putRainIntensity(PixelCoordinates pixelCoordinates, RainIntensity rainIntensity) {
            pixelCoordinateToRainIntensity.put(pixelCoordinates, rainIntensity);
        }

        public RainIntensity getRainIntensity(PixelCoordinates pixelCoordinates) {
            return pixelCoordinateToRainIntensity.get(pixelCoordinates);
        }
    }
}
