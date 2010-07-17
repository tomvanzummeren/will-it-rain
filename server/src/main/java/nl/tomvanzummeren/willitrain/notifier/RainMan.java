package nl.tomvanzummeren.willitrain.notifier;

import nl.tomvanzummeren.willitrain.forecast.Clock;
import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import nl.tomvanzummeren.willitrain.forecast.RainForecast;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The rain man is the main access point to look up rain predictions.
 *
 * @author Tom van Zummeren
 */
@Component
public class RainMan {

    private final RainForecast rainForecast;

    private final GeoLocationTranslator geoLocationTranslator;

    private final Clock clock;

    @Autowired
    public RainMan(RainForecast rainForecast, GeoLocationTranslator geoLocationTranslator, Clock clock) {
        this.rainForecast = rainForecast;
        this.geoLocationTranslator = geoLocationTranslator;
        this.clock = clock;
    }

    public boolean willItRain(GeoLocation geoLocation, int minutesInFuture) {
        DateTime futureTime = clock.minutesInFuture(minutesInFuture);
        return willItRain(geoLocation, futureTime);
    }

    private boolean willItRain(GeoLocation geoLocation, DateTime futureTime) {
        PixelCoordinates pixelCoordinates = geoLocationTranslator.toPixelCoordinates(geoLocation);
        RainIntensity rainIntensity = rainForecast.forRainSnapshot(futureTime).lookupRainIntensity(pixelCoordinates);
        return rainIntensity == RainIntensity.RAIN;
    }
}
