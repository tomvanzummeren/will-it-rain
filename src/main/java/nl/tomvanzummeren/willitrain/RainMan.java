package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import nl.tomvanzummeren.willitrain.forecast.RainForecast;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import nl.tomvanzummeren.willitrain.forecast.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The rain man is the main access point to look up rain predictions.
 *
 * @author Tom van Zummeren
 */
@Component
public class RainMan {

    private RainForecast rainForecast;

    private GeoLocationTranslator geoLocationTranslator;

    @Autowired
    public RainMan(RainForecast rainForecast, GeoLocationTranslator geoLocationTranslator) {
        this.rainForecast = rainForecast;
        this.geoLocationTranslator = geoLocationTranslator;
    }

    public boolean doesItRain(GeoLocation geoLocation, int minutesInFuture) {
        Time futureTime = Time.minutesInFuture(minutesInFuture);

        PixelCoordinates pixelCoordinates = geoLocationTranslator.toPixelCoordinate(geoLocation);

        RainIntensity rainIntensity = rainForecast.lookupRainIntensity(pixelCoordinates, futureTime);
        return rainIntensity == RainIntensity.RAIN;
    }
}
