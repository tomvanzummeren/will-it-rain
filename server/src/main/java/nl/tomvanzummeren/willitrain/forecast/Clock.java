package nl.tomvanzummeren.willitrain.forecast;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Clock to help working with dates and time.
 *
 * @author Tom van Zummeren
 */
@Component
public class Clock {

    public DateTime minutesInFuture(int minutes) {
        Assert.isTrue(minutes > 0, "minutes must be a positive number");
        return createDateTimeAndAddMinutes(minutes);
    }

    public DateTime minutesInPast(int minutes) {
        Assert.isTrue(minutes > 0, "minutes must be a positive number");
        return createDateTimeAndAddMinutes(-minutes);
    }

    private DateTime createDateTimeAndAddMinutes(int minutes) {
        MutableDateTime dateTime = new MutableDateTime();
        dateTime.addMinutes(minutes);
        return dateTime.toDateTime();
    }
}
