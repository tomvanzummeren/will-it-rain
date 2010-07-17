package nl.tomvanzummeren.willitrain.notifier;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import org.springframework.stereotype.Component;

/**
 * @author Tom van Zummeren
 */
@Component
public class GeoLocationTranslator {

    public PixelCoordinates toPixelCoordinates(GeoLocation geoLocation) {
        throw new UnsupportedOperationException("implement");
    }
}
