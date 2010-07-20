package nl.tomvanzummeren.willitrain.forecast.persistence;

import nl.tomvanzummeren.willitrain.forecast.PixelCoordinates;
import nl.tomvanzummeren.willitrain.forecast.RainIntensity;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Represents the rain intensity on a certain pixel on the map at a certain date and time.
 *
 * @author Tom van Zummeren
 */
@Entity
@SuppressWarnings({"JpaObjectClassSignatureInspection"})
public class PixelRainIntensity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime dateTime;

    @Embedded
    private PixelCoordinates pixelCoordinates;

    @Column
    @Enumerated(EnumType.STRING)
    private RainIntensity rainIntensity;

    /**
     * Required by JPA. Do not call in code!
     */
    private PixelRainIntensity() {
    }

    public PixelRainIntensity(DateTime dateTime, PixelCoordinates pixelCoordinates, RainIntensity rainIntensity) {
        this.dateTime = dateTime;
        this.pixelCoordinates = pixelCoordinates;
        this.rainIntensity = rainIntensity;
    }

    public long getId() {
        return id;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public PixelCoordinates getPixelCoordinates() {
        return pixelCoordinates;
    }

    public RainIntensity getRainIntensity() {
        return rainIntensity;
    }
}
