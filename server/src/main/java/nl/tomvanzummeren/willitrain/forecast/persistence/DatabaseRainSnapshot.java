package nl.tomvanzummeren.willitrain.forecast.persistence;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import nl.tomvanzummeren.willitrain.forecast.RainSnapshot;
import org.joda.time.DateTime;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.dao.support.DataAccessUtils.uniqueResult;

/**
 * @author Tom van Zummeren
 */
public class DatabaseRainSnapshot implements RainSnapshot {

    private final DateTime dateTime;

    private final EntityManager entityManager;

    public DatabaseRainSnapshot(DateTime dateTime, EntityManager entityManager) {
        this.dateTime = dateTime;
        this.entityManager = entityManager;
    }

    @Override
    public void storeRainIntensity(PixelCoordinates pixelCoordinates, RainIntensity rainIntensity) {
        entityManager.persist(new PixelRainIntensity(dateTime, pixelCoordinates, rainIntensity));
    }

    @Override
    public RainIntensity lookupRainIntensity(PixelCoordinates pixelCoordinates) {
        String queryString = "select p from PixelRainIntensity p " +
                "where p.pixelCoordinates.x = :x " +
                "and p.pixelCoordinates.y = :y " +
                "and p.dateTime = :dateTime";

        // noinspection unchecked
        List<PixelRainIntensity> results = entityManager.createQuery(queryString)
                .setParameter("x", pixelCoordinates.getX())
                .setParameter("y", pixelCoordinates.getY())
                .setParameter("dateTime", dateTime)
                .getResultList();
        PixelRainIntensity pixelRainIntensity = uniqueResult(results);
        return pixelRainIntensity != null ? pixelRainIntensity.getRainIntensity() : RainIntensity.NONE;
    }

    @Override
    public void delete() {
        entityManager.createQuery("delete from PixelRainIntensity p where p.dateTime = :dateTime")
                .setParameter("dateTime", dateTime)
                .executeUpdate();
    }
}
