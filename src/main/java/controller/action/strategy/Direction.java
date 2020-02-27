/**
 * 
 */
package controller.action.strategy;

/**
 * Enumeration that describes the possible directions.
 *
 */
public enum Direction {
    /**
     * NORTH.
     */
    NORTH(0, 1),
    /**
     * SOUTH.
     */
    SOUTH(0, -1),
    /**
     * EAST.
     */
    EAST(1, 0),
    /**
     * WEST.
     */
    WEST(-1, 0),
    /**
     * NORTHEAST.
     */
    NORTHEAST(1, 1),
    /**
     * SOUTHEAST.
     */
    SOUTHEAST(1, -1),
    /**
     * NORTHWEST.
     */
    NORTHWEST(-1, 1),
    /**
     * SOUTHWEST.
     */
    SOUTHWEST(-1, -1);

    private int xAxis;
    private int yAxis;

    Direction(final int xAxis, final int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    /**
     * @return the variation on the x-axis.
     */
    public int getXVariation() {
        return this.xAxis;
    }

    /**
     * @return the variation on the x-axis.
     */
    public int getYVariation() {
        return this.yAxis;
    }
}
