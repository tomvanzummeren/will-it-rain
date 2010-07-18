package nl.tomvanzummeren.willitrain.forecast;

import javax.persistence.Embeddable;

/**
 * Coordinates of one of the pixels within an image.
 *
 * @author Tom van Zummeren
 */
@Embeddable
@SuppressWarnings({"JpaObjectClassSignatureInspection"})
public class PixelCoordinates {

    private int x;

    private int y;

    /**
     * Required by JPA. Do not call in code!
     */
    private PixelCoordinates() {
    }

    /**
     * Constructs a new {@code PixelCoordinate} based on the given values.
     *
     * @param x horizontal (x) part of the coordinate
     * @param y vertical (y) part of the coordinate
     */
    private PixelCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static PixelCoordinates forPixel(int x, int y) {
        return new PixelCoordinates(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PixelCoordinates that = (PixelCoordinates) o;

        return x == that.x && y == that.y;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    /**
     * Gets the x-coordinate.
     *
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate.
     *
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }
}
