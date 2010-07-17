package nl.tomvanzummeren.willitrain.importer;

import org.joda.time.DateTime;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @author Tom van Zummeren
 */
@Component
public class BuienradarImageLoader {

    public Resource loadRainForecastImage(DateTime time) {
        throw new UnsupportedOperationException("implement");
    }
}
