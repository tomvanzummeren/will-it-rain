package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;

/**
 * Snapshot of the weather at a fixed point in time. The snapshot is composed of {@code Pixelcoordinates}
 * which each represent a small square area on the map.
 *
 * @author Tom van Zummeren
 */
public interface RainSnapshot {

    /**
     * Stores information about the rain intensity for a specific pixel on the map.
     *
     * @param pixelCoordinates pixel on the map
     * @param rainIntensity    rain intensity for the given location
     */
    void storeRainIntensity(PixelCoordinates pixelCoordinates, RainIntensity rainIntensity);

    /**
     * Looks up information about the rain intensity on a specific pixel on the map.
     *
     * @param pixelCoordinates pixel on the map
     * @return the rain intensity on the given pixel. Never returns {@code null}, instead it defaults to
     *         {@link RainIntensity#NONE} when no data is available for the requested location
     */
    RainIntensity lookupRainIntensity(PixelCoordinates pixelCoordinates);

    /**
     * Deletes this entire rain snapshot.
     */
    void delete();
}
