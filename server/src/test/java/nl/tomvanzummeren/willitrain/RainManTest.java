package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.RainForecast;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Tests {@link nl.tomvanzummeren.willitrain.RainMan}.
 *
 * @author Rob van der Linden Vooren
 */
public class RainManTest {

    GeoLocationTranslator mockGeoLocationTranslator = mock(GeoLocationTranslator.class);
    RainForecast mockRainForecast = mock(RainForecast.class);
    RainMan rainMan = new RainMan(mockRainForecast, mockGeoLocationTranslator);

    @Test
    public void it() {
        GeoLocation location = anyGeoLocation();

        boolean willRain = rainMan.willItRain(location, 5);

        assertTrue("will it rain", willRain);
    }

    private GeoLocation anyGeoLocation() {
        Random random = new Random();
        return new GeoLocation(new BigDecimal(random.nextInt()), new BigDecimal(random.nextLong()));
    }
}
