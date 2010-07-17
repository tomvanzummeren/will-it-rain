package nl.tomvanzummeren.willitrain;

import static java.lang.Float.*;

/**
 * Represents a specific location on earth.
 *
 * @author Tom van Zummeren
 */
public class GeoLocation {

    private final float latitude;

    private final float longitude;

    /**
     * Constructs a new {@code GeoLocation} from a longitude and a latitude.
     *
     * @param latitude  latitude of the geo-location
     * @param longitude longitude of the geo-location
     */
    public GeoLocation(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeoLocation that = (GeoLocation) o;

        return compare(that.latitude, latitude) == 0 && compare(that.longitude, longitude) == 0;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = (latitude != +0.0f ? floatToIntBits(latitude) : 0);
        result = 31 * result + (longitude != +0.0f ? floatToIntBits(longitude) : 0);
        return result;
    }

    /**
     * Gets the latitude for the geo-location.
     *
     * @return latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Gets the latitude for the geo-location.
     *
     * @return latitude
     */
    public float getLongitude() {
        return longitude;
    }
}
