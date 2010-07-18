package nl.tomvanzummeren.willitrain.forecast.persistence;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import nl.tomvanzummeren.willitrain.forecast.RainSnapshot;
import org.joda.time.DateTime;

import javax.persistence.EntityManager;

/**
 * @author Tom van Zummeren
 */
public class DatabaseRainSnapshot implements RainSnapshot {

    private final DateTime dateTime;

    private final EntityManager entityManager;

    public DatabaseRainSnapshot(DateTime dateTime, EntityManager entityManager) {
        this.dateTime = dateTime;
        this.entityManager = entityManager;
    }

    @Override
    public void storeRainIntensity(PixelCoordinates pixelCoordinates, RainIntensity rainIntensity) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public RainIntensity lookupRainIntensity(PixelCoordinates pixelCoordinates) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("not implemented");
    }
}
