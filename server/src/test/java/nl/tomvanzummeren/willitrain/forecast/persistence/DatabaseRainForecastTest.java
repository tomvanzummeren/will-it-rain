package nl.tomvanzummeren.willitrain.forecast.persistence;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static nl.tomvanzummeren.willitrain.forecast.PixelCoordinates.forPixel;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@link nl.tomvanzummeren.willitrain.forecast.persistence.DatabaseRainForecast}.
 *
 * @author Tom van Zummeren
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@TransactionConfiguration
@Transactional
public class DatabaseRainForecastTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DatabaseRainForecast rainForecast;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void storesRainIntensityInDatabase() throws Exception {
        DateTime now = new DateTime();

        rainForecast.forRainSnapshot(now).storeRainIntensity(forPixel(10, 20), RainIntensity.RAIN);

        // noinspection unchecked
        List<PixelRainIntensity> results = entityManager.createQuery("select p from PixelRainIntensity p").getResultList();
        assertEquals(1, results.size());

        PixelRainIntensity pixelRainIntensity = results.get(0);
        assertEquals(10, pixelRainIntensity.getPixelCoordinates().getX());
        assertEquals(20, pixelRainIntensity.getPixelCoordinates().getY());
        assertEquals(RainIntensity.RAIN, pixelRainIntensity.getRainIntensity());
        assertEquals(now, pixelRainIntensity.getDateTime());
    }

    @Test
    public void looksUpRainIntensityFromDatabase() throws Exception {
        MutableDateTime dateTime = new MutableDateTime();
        DateTime now = dateTime.toDateTime();
        dateTime.addMinutes(5);
        DateTime fiveMinutesInFuture = dateTime.toDateTime();

        PixelCoordinates pixel1 = forPixel(20, 10);
        PixelCoordinates pixel2 = forPixel(15, 25);

        entityManager.persist(new PixelRainIntensity(now, pixel1, RainIntensity.RAIN));
        entityManager.persist(new PixelRainIntensity(now, pixel2, RainIntensity.RAIN));
        entityManager.persist(new PixelRainIntensity(fiveMinutesInFuture, pixel1, RainIntensity.RAIN));

        RainIntensity intensity = rainForecast.forRainSnapshot(fiveMinutesInFuture).lookupRainIntensity(pixel1);
        assertEquals(RainIntensity.RAIN, intensity);

        intensity = rainForecast.forRainSnapshot(fiveMinutesInFuture).lookupRainIntensity(pixel2);
        assertEquals(RainIntensity.NONE, intensity);
    }

    @Test
    public void deletesRainSnapshot() throws Exception {
        MutableDateTime dateTime = new MutableDateTime();
        DateTime now = dateTime.toDateTime();
        dateTime.addMinutes(5);
        DateTime fiveMinutesInFuture = dateTime.toDateTime();

        PixelCoordinates pixel1 = forPixel(20, 10);
        PixelCoordinates pixel2 = forPixel(15, 25);

        entityManager.persist(new PixelRainIntensity(now, pixel1, RainIntensity.RAIN));
        entityManager.persist(new PixelRainIntensity(now, pixel2, RainIntensity.RAIN));
        PixelRainIntensity intensity = new PixelRainIntensity(fiveMinutesInFuture, pixel1, RainIntensity.RAIN);
        entityManager.persist(intensity);

        rainForecast.forRainSnapshot(now).delete();

        // noinspection unchecked
        List<PixelRainIntensity> results = entityManager.createQuery("select p from PixelRainIntensity p").getResultList();
        assertEquals(1, results.size());
        assertEquals(intensity.getId(), results.get(0).getId());
    }
}
