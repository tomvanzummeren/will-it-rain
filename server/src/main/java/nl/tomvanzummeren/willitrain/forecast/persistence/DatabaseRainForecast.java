package nl.tomvanzummeren.willitrain.forecast.persistence;

import nl.tomvanzummeren.willitrain.forecast.RainForecast;
import nl.tomvanzummeren.willitrain.forecast.RainSnapshot;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Uses a database to store and lookup the rain forecast.
 *
 * @author Tom van Zummeren
 */
@Repository
public class DatabaseRainForecast implements RainForecast {

    private EntityManager entityManager;

    @Override
    public RainSnapshot forRainSnapshot(DateTime dateTime) {
        return new DatabaseRainSnapshot(dateTime, entityManager);
    }
}
