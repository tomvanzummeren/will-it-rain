package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinate;
import org.springframework.stereotype.Component;

/**
* @author Tom van Zummeren
*/
@Component
public class GeoLocationTranslator {
    public PixelCoordinate toPixelCoordinate(GeoLocation geoLocation) {
        throw new UnsupportedOperationException("implement");
    }
}
