package nl.tomvanzummeren.willitrain.forecast;

/**
 * Coordinate of one of the pixels contained by a {@code WeatherSnapshot}.
 *
 * @author Tom van Zummeren
 * @see nl.tomvanzummeren.willitrain.WeatherSnapshot
 */
public class PixelCoordinate {

    private int x;

    private int y;

    /**
     * Constructs a new {@code PixelCoordinate} based on the given values.
     *
     * @param x horizontal (x) part of the coordinate
     * @param y vertical (y) part of the coordinate
     */
    public PixelCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PixelCoordinate that = (PixelCoordinate) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
