package nl.tomvanzummeren.willitrain.forecast;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Coordinates of one of the pixels within an image.
 *
 * @author Tom van Zummeren
 */
@Embeddable
@SuppressWarnings({"JpaObjectClassSignatureInspection"})
public class PixelCoordinates {

    @Column
    private int x;

    @Column
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
        Assert.isTrue(x >= 0, "x-coordinate cannot be negative");
        Assert.isTrue(y >= 0, "y-coordinate cannot be negative");
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
