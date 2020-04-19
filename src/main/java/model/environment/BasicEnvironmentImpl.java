package model.environment;

import model.environment.exceptions.OutOfEnviromentException;
import model.environment.position.Position;

/**
 * Represent a simple environment. 
 */
public class BasicEnvironmentImpl extends AbstractEnvironment implements BasicEnvironment {

    private int morningFoodQuantity;
    private final int dailyFoodQuantityModification;

    /**
     * @param xDimension
     * environment width.
     * @param yDimension
     * environment height.
     * @param morningFoodQuantity
     * morning food quantity.
     * @param dailyFoodQuantityModification
     * daily food variation.
     */
    public BasicEnvironmentImpl(final int xDimension, final int yDimension, final int morningFoodQuantity, final int dailyFoodQuantityModification) {
        super(xDimension, yDimension);
        this.morningFoodQuantity = morningFoodQuantity;
        this.dailyFoodQuantityModification = dailyFoodQuantityModification;
    }

    @Override
    public final int getMorningFoodQuantity() {
        return this.morningFoodQuantity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextDay() {
        // deletes all current food in the environment
        super.clear();
        this.morningFoodQuantity += this.dailyFoodQuantityModification;
        // cannot have < 0 food quantity
        if (this.morningFoodQuantity < 0) {
            this.morningFoodQuantity = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkPosition(final Position position) throws OutOfEnviromentException {
        if (position.getX() < 0 || position.getX() > super.getDimension().getX()
                || position.getY() < 0 || position.getY() > super.getDimension().getY()) {
            throw new OutOfEnviromentException();
        }
        return true;
    }

    /**
     * A Sting representation of a BasicEnvironment.
     */
    @Override
    public String toString() {
        return super.toString() + "BasicEnvironmentImpl [morningFoodQuantity=" + morningFoodQuantity + ", dailyFoodQuantityModification="
                + dailyFoodQuantityModification + "]";
    }
}
