/**
 * 
 */
package model.environment.position;


/**
 * Represent a position on a 2D space.
 *
 */
public interface Position {

    /**
     * @return the X value of the position.
     */
    double getX();

    /**
     * @param x
     *         the x value of the position to set.
     */
    void setX(double x);

    /**
     * @return the Y value of the position.
     */
    double getY();

    /**
     * @param y
     *         the y value of the position to set.
     */
    void setY(double y);

    /**
     * @return the Position in a 2D space.
     */
    Position getPosition();

    /**
     * @param x
     *        the x value of the position to set.
     * @param y
     *        the y value of the position to set.
     */
    void setPosition(double x, double y);

}
