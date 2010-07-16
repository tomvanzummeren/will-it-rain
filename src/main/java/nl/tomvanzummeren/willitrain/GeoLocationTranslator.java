package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import org.springframework.stereotype.Component;

/**
* @author Tom van Zummeren
*/
@Component
public class GeoLocationTranslator {
    public PixelCoordinates toPixelCoordinate(GeoLocation geoLocation) {
        throw new UnsupportedOperationException("implement");
    }
}
