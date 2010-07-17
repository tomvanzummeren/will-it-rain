package nl.tomvanzummeren.willitrain.forecast;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

/**
 * Represents a specific time on a day
 *
 * @author Tom van Zummeren
 */
public class Time {

    private Time() {
    }

    public static DateTime minutesInFuture(int minutes) {
        MutableDateTime dateTime = new MutableDateTime();
        dateTime.addMinutes(minutes);
        return dateTime.toDateTime();
    }

    public static DateTime minutesInPast(int minutes) {
        return minutesInFuture(-minutes);
    }
}
