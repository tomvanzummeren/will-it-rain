package nl.tomvanzummeren.willitrain.forecast;

import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory implementation of a rain forecast. Used for testing purposes.
 *
 * @author Tom van Zummeren
 */
public class InMemoryRainForecast implements RainForecast {

    private Map<DateTime, RainSnapshot> timeToRainSnapshot = new HashMap<DateTime, RainSnapshot>();

    /**
     * {@inheritDoc}
     */
    public RainSnapshot forRainSnapshot(DateTime dateTime) {
        RainSnapshot rainSnapshot = timeToRainSnapshot.get(dateTime);
        if (rainSnapshot == null) {
            rainSnapshot = new InMemoryRainSnapshot();
            timeToRainSnapshot.put(dateTime, rainSnapshot);
        }
        return rainSnapshot;
    }

}
