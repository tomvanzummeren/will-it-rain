package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.Time;
import nl.tomvanzummeren.willitrain.importer.BuienRadarRainForecastImporter;
import nl.tomvanzummeren.willitrain.importer.BuienradarImageLoader;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static junit.framework.Assert.*;
import static nl.tomvanzummeren.willitrain.forecast.PixelCoordinates.*;
import static nl.tomvanzummeren.willitrain.forecast.RainIntensity.*;
import static org.mockito.Mockito.*;

/**
 * Tests {@link }
 *
 * @author Tom van Zummeren
 */
public class BuienRadarRainForecastImporterTest {

    private BuienRadarRainForecastImporter importerBuienRadar;

    private InMemoryRainForecast rainForecast;

    private BuienradarImageLoader mockImageLoader;

    @Before
    public void setUp() throws Exception {
        rainForecast = new InMemoryRainForecast();
        mockImageLoader = mock(BuienradarImageLoader.class);

        importerBuienRadar = new BuienRadarRainForecastImporter(rainForecast, mockImageLoader);
    }

    @Test
    public void importsRainForecastImage() throws Exception {
        DateTime timeInFuture = Time.minutesInFuture(5);

        when(mockImageLoader.loadRainForecastImage(timeInFuture)).thenReturn(new ClassPathResource("rain-forecast.gif"));

        importerBuienRadar.importForTimeInFuture(timeInFuture);

        RainSnapshot snapshot = rainForecast.forRainSnapshot(timeInFuture);
        // Check a few pixels that should indicate rain
        assertEquals(RAIN, snapshot.lookupRainIntensity(forPixel(176, 285)));
        assertEquals(RAIN, snapshot.lookupRainIntensity(forPixel(116, 347)));
        assertEquals(RAIN, snapshot.lookupRainIntensity(forPixel(230, 485)));
        // Check a few pixels that should not indicate rain
        assertEquals(NONE, snapshot.lookupRainIntensity(forPixel(100, 100)));
        assertEquals(NONE, snapshot.lookupRainIntensity(forPixel(285, 188)));
        assertEquals(NONE, snapshot.lookupRainIntensity(forPixel(96, 426)));
        // Check a pixel that should not indicate rain because it's part of the time label in the top-left corner of the image
        assertEquals(NONE, snapshot.lookupRainIntensity(forPixel(9, 11)));
        // Check a pixel that should not indicate rain because it's part of the time label in the bottom-left corner of the image
        assertEquals(NONE, snapshot.lookupRainIntensity(forPixel(9, 509)));
    }
}