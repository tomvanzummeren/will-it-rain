package nl.tomvanzummeren.willitrain.forecast;

/**
 * Represents a specific time on a day
 *
 * @author Tom van Zummeren
 */
public class Time {

    private int hours;

    private int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public static Time minutesInFuture(int minutesInFuture) {
        throw new UnsupportedOperationException("implement");
    }
}
