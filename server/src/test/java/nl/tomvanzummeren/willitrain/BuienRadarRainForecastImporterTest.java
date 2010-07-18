package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.RainSnapshot;
import nl.tomvanzummeren.willitrain.importer.BuienradarImageLoader;
import nl.tomvanzummeren.willitrain.importer.BuienradarRainForecastImporter;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static nl.tomvanzummeren.willitrain.forecast.PixelCoordinates.forPixel;
import static nl.tomvanzummeren.willitrain.forecast.RainIntensity.NONE;
import static nl.tomvanzummeren.willitrain.forecast.RainIntensity.RAIN;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests {@link nl.tomvanzummeren.willitrain.importer.BuienradarRainForecastImporter}.
 *
 * @author Tom van Zummeren
 */
public class BuienRadarRainForecastImporterTest {

    private BuienradarRainForecastImporter importerBuienradar;

    private InMemoryRainForecast rainForecast;

    private BuienradarImageLoader mockImageLoader;

    @Before
    public void setUp() throws Exception {
        rainForecast = new InMemoryRainForecast();
        mockImageLoader = mock(BuienradarImageLoader.class);

        importerBuienradar = new BuienradarRainForecastImporter(rainForecast, mockImageLoader);
    }

    @Test
    public void importsRainForecastImage() throws Exception {
        DateTime dateTimeInFuture = new DateTime();

        when(mockImageLoader.loadRainForecastImage(dateTimeInFuture)).thenReturn(new ClassPathResource("rain-forecast.gif"));

        importerBuienradar.importSnapshotForTime(dateTimeInFuture);

        RainSnapshot snapshot = rainForecast.forRainSnapshot(dateTimeInFuture);
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
        // Check a pixel that should not indicate rain because it's part of the Buienradar label in the bottom-left corner of the image
        assertEquals(NONE, snapshot.lookupRainIntensity(forPixel(9, 509)));
    }
}
