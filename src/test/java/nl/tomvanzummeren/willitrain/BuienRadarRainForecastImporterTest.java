package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import nl.tomvanzummeren.willitrain.forecast.Time;
import nl.tomvanzummeren.willitrain.importer.BuienRadarRainForecastImporter;
import nl.tomvanzummeren.willitrain.importer.BuienradarImageLoader;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static junit.framework.Assert.*;
import static nl.tomvanzummeren.willitrain.forecast.PixelCoordinates.*;
import static org.mockito.Mockito.*;

/**
 * Tests the 
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
        Time timeInFuture = new Time(12, 0);

        when(mockImageLoader.loadRainForecastImage(timeInFuture)).thenReturn(new ClassPathResource("rain-forecast.gif"));

        importerBuienRadar.importForTimeInFuture(timeInFuture);

        // Check a few pixels that should indicate rain
        assertEquals(RainIntensity.RAIN, rainForecast.lookupRainIntensity(pixel(176, 285), timeInFuture));
        assertEquals(RainIntensity.RAIN, rainForecast.lookupRainIntensity(pixel(116, 347), timeInFuture));
        assertEquals(RainIntensity.RAIN, rainForecast.lookupRainIntensity(pixel(230, 485), timeInFuture));
        // Check a few pixels that should not indicate rain
        assertEquals(RainIntensity.NONE, rainForecast.lookupRainIntensity(pixel(100, 100), timeInFuture));
        assertEquals(RainIntensity.NONE, rainForecast.lookupRainIntensity(pixel(285, 188), timeInFuture));
        assertEquals(RainIntensity.NONE, rainForecast.lookupRainIntensity(pixel(96, 426), timeInFuture));
        // Check a pixel that should not indicate rain because it's part of the time label in the top-left corner of the image
        assertEquals(RainIntensity.NONE, rainForecast.lookupRainIntensity(pixel(9, 11), timeInFuture));
        // Check a pixel that should not indicate rain because it's part of the time label in the bottom-left corner of the image
        assertEquals(RainIntensity.NONE, rainForecast.lookupRainIntensity(pixel(9, 509), timeInFuture));
    }
}
