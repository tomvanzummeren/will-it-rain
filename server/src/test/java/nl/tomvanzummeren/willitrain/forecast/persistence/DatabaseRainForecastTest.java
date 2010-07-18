package nl.tomvanzummeren.willitrain.forecast.persistence;

import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static nl.tomvanzummeren.willitrain.forecast.PixelCoordinates.forPixel;

/**
 * Tests {@link nl.tomvanzummeren.willitrain.forecast.persistence.DatabaseRainForecast}.
 *
 * @author Tom van Zummeren
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@TransactionConfiguration
@Transactional
@Ignore
public class DatabaseRainForecastTest {

    private DatabaseRainForecast rainForecast;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        rainForecast = new DatabaseRainForecast();
    }

    @Test
    public void storesRainIntensityInDatabase() throws Exception {
        entityManager.persist(new PixelRainIntensity(new DateTime(), forPixel(1, 2), RainIntensity.RAIN));
        //rainForecast.forRainSnapshot(new DateTime()).storeRainIntensity(forPixel(1, 2), RainIntensity.RAIN);
    }
}
