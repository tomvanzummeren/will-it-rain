package nl.tomvanzummeren.willitrain.forecast;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Component;

/**
 * Clock to help working with dates and time.
 *
 * @author Tom van Zummeren
 */
@Component
public class Clock {

    public DateTime minutesInFuture(int minutes) {
        MutableDateTime dateTime = new MutableDateTime();
        dateTime.addMinutes(minutes);
        return dateTime.toDateTime();
    }

    public DateTime minutesInPast(int minutes) {
        return minutesInFuture(-minutes);
    }
}
