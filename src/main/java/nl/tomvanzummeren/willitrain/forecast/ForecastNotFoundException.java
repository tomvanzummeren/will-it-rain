package nl.tomvanzummeren.willitrain.forecast;

/**
 * Thrown when no weather forecast data is available for requested time.
 *
 * @author Tom van Zummeren
 */
public class ForecastNotFoundException extends RuntimeException {

    public ForecastNotFoundException() {
    }
}
