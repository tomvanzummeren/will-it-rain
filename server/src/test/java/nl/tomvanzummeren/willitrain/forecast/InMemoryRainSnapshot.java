package nl.tomvanzummeren.willitrain.forecast;

import java.util.HashMap;
import java.util.Map;

/**
* @author Tom van Zummeren
*/
public class InMemoryRainSnapshot implements RainSnapshot {

    private Map<PixelCoordinates, RainIntensity> pixelCoordinateToRainIntensity = new HashMap<PixelCoordinates, RainIntensity>();

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
