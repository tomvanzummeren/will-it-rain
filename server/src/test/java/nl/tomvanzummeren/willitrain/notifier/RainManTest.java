package nl.tomvanzummeren.willitrain.notifier;

import nl.tomvanzummeren.willitrain.forecast.*;
import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests {@link nl.tomvanzummeren.willitrain.notifier.RainMan}.
 *
 * @author Rob van der Linden Vooren
 */
public class RainManTest {

    RainForecast mockRainForecast = mock(RainForecast.class);

    GeoLocationTranslator mockGeoLocationTranslator = mock(GeoLocationTranslator.class);

    Clock mockClock = mock(Clock.class);

    RainMan rainMan = new RainMan(mockRainForecast, mockGeoLocationTranslator, mockClock);

    @Test
    public void determinesItWillRainWhenRainSnapshotIndicatesRain() throws Exception {
        GeoLocation geoLocation = anyGeoLocation();
        int fiveMinutesInFuture = 5;
        DateTime timeIn5Minutes = new DateTime();
        PixelCoordinates pixelCoordinates = anyPixelCoordinate();

        RainSnapshot rainSnapshot = new InMemoryRainSnapshot();
        rainSnapshot.storeRainIntensity(pixelCoordinates, RainIntensity.RAIN);

        when(mockClock.minutesInFuture(fiveMinutesInFuture)).thenReturn(timeIn5Minutes);
        when(mockGeoLocationTranslator.toPixelCoordinates(geoLocation)).thenReturn(pixelCoordinates);
        when(mockRainForecast.forRainSnapshot(timeIn5Minutes)).thenReturn(rainSnapshot);

        boolean willRain = rainMan.willItRain(geoLocation, fiveMinutesInFuture);

        assertTrue("will rain", willRain);
    }

    private static GeoLocation anyGeoLocation() {
        return new GeoLocation(new BigDecimal(1L), new BigDecimal(1L));
    }

    private static PixelCoordinates anyPixelCoordinate() {
        return PixelCoordinates.forPixel(0, 0);
    }
}
