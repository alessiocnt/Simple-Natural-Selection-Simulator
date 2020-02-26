package model.entity;

import java.awt.geom.Point2D;

public class PositionImpl implements Position {

    private Point2D position;

    /**
     * @param x
     *        the x value of the position to set.
     * @param y
     *        the y value of the position to set.
     */
    public PositionImpl(final double x, final double y) {
        this.position = new Point2D.Double(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return this.position.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(final double x) {
        this.setPosition(x, this.position.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return this.position.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(final double y) {
        this.setPosition(this.position.getX(), y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final double x, final double y) {
        this.position.setLocation(x, y);
    }

    /**
     * toString for a Position.
     */
    @Override
    public String toString() {
        return "[" + position.getX() + ", " + position.getY() + "]";
    }

}
