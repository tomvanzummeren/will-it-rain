package nl.tomvanzummeren.willitrain;

/**
 * Represents a specific location on earth.
 *
 * @author Tom van Zummeren
 */
public class GeoLocation {

    private float latitude;

    private float longitude;

    public GeoLocation(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
