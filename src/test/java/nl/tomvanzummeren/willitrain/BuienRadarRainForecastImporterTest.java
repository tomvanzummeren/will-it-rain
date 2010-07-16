package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import nl.tomvanzummeren.willitrain.forecast.Time;
import nl.tomvanzummeren.willitrain.importer.BuienRadarRainForecastImporter;
import nl.tomvanzummeren.willitrain.importer.BuienradarImageLoader;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
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

        // Check a pixel that should indicate rain
        assertEquals(RainIntensity.RAIN, rainForecast.lookupRainIntensity(new PixelCoordinates(176, 285), timeInFuture));
        // Check a pixel that should not indicate rain
        assertEquals(RainIntensity.NONE, rainForecast.lookupRainIntensity(new PixelCoordinates(100, 100), timeInFuture));
        // Check a pixel that should not indicate rain because it's part of the time label in the top-left corner of the image
        assertEquals(RainIntensity.NONE, rainForecast.lookupRainIntensity(new PixelCoordinates(9, 11), timeInFuture));
        // Check a pixel that should not indicate rain because it's part of the time label in the bottom-left corner of the image
        assertEquals(RainIntensity.NONE, rainForecast.lookupRainIntensity(new PixelCoordinates(9, 509), timeInFuture));
    }
}
