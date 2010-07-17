package nl.tomvanzummeren.willitrain;

import java.math.BigDecimal;

/**
 * Represents a specific location on earth.
 *
 * @author Tom van Zummeren
 */
public class GeoLocation {

    private final BigDecimal latitude;

    private final BigDecimal longitude;

    /**
     * Constructs a new {@code GeoLocation} from a longitude and a latitude.
     *
     * @param latitude  latitude of the geo-location
     * @param longitude longitude of the geo-location
     */
    public GeoLocation(BigDecimal latitude, BigDecimal longitude) {
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

        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = latitude != null ? latitude.hashCode() : 0;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }

    /**
     * Gets the latitude for the geo-location.
     *
     * @return latitude
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * Gets the latitude for the geo-location.
     *
     * @return latitude
     */
    public BigDecimal getLongitude() {
        return longitude;
    }
}
