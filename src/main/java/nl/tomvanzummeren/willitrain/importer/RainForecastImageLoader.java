package nl.tomvanzummeren.willitrain.importer;

import nl.tomvanzummeren.willitrain.forecast.Time;
import org.springframework.core.io.Resource;

/**
 * @author Tom van Zummeren
 */
public interface RainForecastImageLoader {

    Resource loadRainForecastImage(Time time);
}
