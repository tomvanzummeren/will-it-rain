package nl.tomvanzummeren.willitrain.notifier;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import nl.tomvanzummeren.willitrain.forecast.RainForecast;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import nl.tomvanzummeren.willitrain.forecast.Time;
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

    @Autowired
    public RainMan(RainForecast rainForecast, GeoLocationTranslator geoLocationTranslator) {
        this.rainForecast = rainForecast;
        this.geoLocationTranslator = geoLocationTranslator;
    }

    public boolean willItRain(GeoLocation geoLocation, int minutesInFuture) {
        DateTime futureTime = Time.minutesInFuture(minutesInFuture);

        PixelCoordinates pixelCoordinates = geoLocationTranslator.toPixelCoordinate(geoLocation);

        RainIntensity rainIntensity = rainForecast.forRainSnapshot(futureTime).lookupRainIntensity(pixelCoordinates);
        return rainIntensity == RainIntensity.RAIN;
    }
}
