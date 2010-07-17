package nl.tomvanzummeren.willitrain.importer;

import nl.tomvanzummeren.willitrain.forecast.RainForecast;
import nl.tomvanzummeren.willitrain.forecast.Time;
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

    private final RainForecast rainForecast;

    @Autowired
    public RainForecastUpdater(BuienradarRainForecastImporter rainForecastImporter, RainForecast rainForecast) {
        this.rainForecastImporter = rainForecastImporter;
        this.rainForecast = rainForecast;
    }

    public void updateRainForecast() {
        DateTime fiveMinutesAgo = Time.minutesInPast(KEEP_FORECAST_MINUTES_IN_PAST);
        rainForecast.forRainSnapshot(fiveMinutesAgo).delete();

        try {
            DateTime inFuture = Time.minutesInFuture(KEEP_FORECAST_MINUTES_IN_FUTURE);
            rainForecastImporter.importForTimeInFuture(inFuture);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ImageReadException e) {
            throw new RuntimeException(e);
        }
    }
}
