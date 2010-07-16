package nl.tomvanzummeren.willitrain;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinate;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import nl.tomvanzummeren.willitrain.forecast.Time;
import nl.tomvanzummeren.willitrain.importer.RainForecastImageLoader;
import nl.tomvanzummeren.willitrain.importer.RainForecastImporter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Tom van Zummeren
 */
public class RainForecastImporterTest {

    private RainForecastImporter importer;

    private InMemoryRainForecast rainForecast;

    private RainForecastImageLoader mockImageLoader;

    @Before
    public void setUp() throws Exception {
        rainForecast = new InMemoryRainForecast();
        mockImageLoader = mock(RainForecastImageLoader.class);

        importer = new RainForecastImporter(rainForecast, mockImageLoader);
    }

    @Test
    public void importsRainForecastImage() throws Exception {
        Time timeInFuture = new Time(12, 0);

        when(mockImageLoader.loadRainForecastImage(timeInFuture)).thenReturn(new ClassPathResource("rain-forecast.gif"));

        importer.importForTimeInFuture(timeInFuture);

        assertEquals(RainIntensity.RAIN, rainForecast.lookupRainIntensity(new PixelCoordinate(176, 285), timeInFuture));
        assertEquals(RainIntensity.NONE, rainForecast.lookupRainIntensity(new PixelCoordinate(100, 100), timeInFuture));
    }
}
