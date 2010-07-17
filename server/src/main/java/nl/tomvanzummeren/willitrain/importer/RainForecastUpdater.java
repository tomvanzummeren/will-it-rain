package nl.tomvanzummeren.willitrain.importer;

import nl.tomvanzummeren.willitrain.forecast.Clock;
import org.apache.sanselan.ImageReadException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Updates the entire local rain forecast using a {@link BuienradarRainForecastImporter}. The only public method
 * {@link #updateRainForecast()} is called each 5 minutes to keep the rain forecast up-to-date.
 *
 * @author Tom van Zummeren
 */
@Component
public class RainForecastUpdater {

    private static final int KEEP_FORECAST_MINUTES_IN_FUTURE = 115;

    private static final int KEEP_FORECAST_MINUTES_IN_PAST = 5;

    private final BuienradarRainForecastImporter rainForecastImporter;

    private final Clock clock;

    @Autowired
    public RainForecastUpdater(BuienradarRainForecastImporter rainForecastImporter, Clock clock) {
        this.rainForecastImporter = rainForecastImporter;
        this.clock = clock;
    }

    public void updateRainForecast() {
        DateTime fiveMinutesAgo = clock.minutesInPast(KEEP_FORECAST_MINUTES_IN_PAST);
        rainForecastImporter.deleteSnapshotForTime(fiveMinutesAgo);

        try {
            DateTime inFuture = clock.minutesInFuture(KEEP_FORECAST_MINUTES_IN_FUTURE);
            rainForecastImporter.importSnapshotForTime(inFuture);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ImageReadException e) {
            throw new RuntimeException(e);
        }
    }
}
