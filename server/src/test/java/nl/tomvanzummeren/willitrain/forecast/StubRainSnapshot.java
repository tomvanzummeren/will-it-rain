package nl.tomvanzummeren.willitrain.forecast;

import java.util.HashMap;
import java.util.Map;

/**
 * Stub {@link nl.tomvanzummeren.willitrain.forecast.RainSnapshot} for testing purposes.
 *
 * @author Rob van der Linden Vooren
 */
public class StubRainSnapshot implements RainSnapshot {

    private final Map<PixelCoordinates, RainIntensity> rainIntensity = new HashMap<PixelCoordinates, RainIntensity>();

    public StubRainSnapshot(PixelCoordinates pixelCoordinates, RainIntensity rainIntensity) {
        this.rainIntensity.put(pixelCoordinates, rainIntensity);
    }

    @Override
    public void storeRainIntensity(PixelCoordinates pixelCoordinates, RainIntensity rainIntensity) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public RainIntensity lookupRainIntensity(PixelCoordinates pixelCoordinates) {
        return rainIntensity.get(pixelCoordinates);
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("not implemented");
    }
}
