package nl.tomvanzummeren.willitrain.forecast;

import nl.tomvanzummeren.willitrain.RainSnapshot;
import org.joda.time.DateTime;

import javax.persistence.EntityManager;

/**
 * @author Tom van Zummeren
 */
public class DatabaseRainSnapshot implements RainSnapshot {

    private final DateTime time;
    
    private final EntityManager entityManager;

    public DatabaseRainSnapshot(DateTime time, EntityManager entityManager) {
        this.time = time;
        this.entityManager = entityManager;
    }

    @Override
    public void storeRainIntensity(PixelCoordinates pixelCoordinates, RainIntensity rainIntensity) {
        throw new UnsupportedOperationException("implement");
    }

    @Override
    public RainIntensity lookupRainIntensity(PixelCoordinates pixelCoordinates) {
        throw new UnsupportedOperationException("implement");
    }
}
