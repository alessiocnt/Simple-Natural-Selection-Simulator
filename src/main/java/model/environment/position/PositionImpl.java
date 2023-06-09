package model.environment.position;

import java.awt.geom.Point2D;

/**
 * Position implementation.
 *
 */
public class PositionImpl implements Position {

    private final Point2D position;

    /**
     * @param x
     *        the x value of the position to set.
     * @param y
     *        the y value of the position to set.
     */
    public PositionImpl(final double x, final double y) {
        this.position = new Point2D.Double(x, y);
    }

    @Override
    public final double getX() {
        return this.position.getX();
    }

    @Override
    public final void setX(final double x) {
        this.setPosition(x, this.position.getY());
    }

    @Override
    public final double getY() {
        return this.position.getY();
    }

    @Override
    public final void setY(final double y) {
        this.setPosition(this.position.getX(), y);
    }

    @Override
    public final void setPosition(final double x, final double y) {
        this.position.setLocation(x, y);
    }


    /**
     * HashCode method.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        return result;
    }

    /**
     * Equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PositionImpl other = (PositionImpl) obj;
        if (position == null) {
            if (other.position != null) {
                return false;
            }
        } else if (!position.equals(other.position)) {
            return false;
        }
        return true;
    }

    /**
     * toString for a Position.
     */
    @Override
    public String toString() {
        return "[" + position.getX() + ", " + position.getY() + "]";
    }

}
