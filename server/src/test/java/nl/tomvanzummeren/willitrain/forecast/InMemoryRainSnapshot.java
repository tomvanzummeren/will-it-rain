package nl.tomvanzummeren.willitrain.forecast;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory {@link nl.tomvanzummeren.willitrain.forecast.RainSnapshot} for testing purposes.
 *
 * @author Tom van Zummeren
 * @author Rob van der Linden Vooren
 */
public class InMemoryRainSnapshot implements RainSnapshot {

    private final Map<PixelCoordinates, RainIntensity> pixelCoordinateToRainIntensity;

    public InMemoryRainSnapshot() {
        pixelCoordinateToRainIntensity = new HashMap<PixelCoordinates, RainIntensity>();
    }

    public InMemoryRainSnapshot(PixelCoordinates pixelCoordinates, RainIntensity rainIntensity) {
        this();
        pixelCoordinateToRainIntensity.put(pixelCoordinates, rainIntensity);
    }

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
