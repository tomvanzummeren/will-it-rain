package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import nl.tomvanzummeren.willitrain.forecast.RainForecast;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory implementation of a rain forecast. Used for testing purposes.
 *
 * @author Tom van Zummeren
 */
public class InMemoryRainForecast implements RainForecast {

    private final Map<DateTime, RainSnapshot> timeToRainSnapshot = new HashMap<DateTime, RainSnapshot>();

    /**
     * {@inheritDoc}
     */
    public RainSnapshot forRainSnapshot(DateTime time) {
        RainSnapshot rainSnapshot = timeToRainSnapshot.get(time);
        if (rainSnapshot == null) {
            rainSnapshot = new InMemoryRainSnapshot();
            timeToRainSnapshot.put(time, rainSnapshot);
        }
        return rainSnapshot;
    }

    private static class InMemoryRainSnapshot implements RainSnapshot {

        private final Map<PixelCoordinates, RainIntensity> pixelCoordinateToRainIntensity = new HashMap<PixelCoordinates, RainIntensity>();

        public void storeRainIntensity(PixelCoordinates pixelCoordinates, RainIntensity rainIntensity) {
            pixelCoordinateToRainIntensity.put(pixelCoordinates, rainIntensity);
        }

        public RainIntensity lookupRainIntensity(PixelCoordinates pixelCoordinates) {
            RainIntensity intensity = pixelCoordinateToRainIntensity.get(pixelCoordinates);
            return intensity == null ? RainIntensity.NONE : intensity;
        }

        @Override
        public void delete() {
            throw new UnsupportedOperationException("not implemented");
        }
    }
}
