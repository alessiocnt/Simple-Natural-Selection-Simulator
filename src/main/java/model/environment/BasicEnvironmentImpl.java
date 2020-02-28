package model.environment;


public class BasicEnvironmentImpl extends AbstractEnvironment implements BasicEnvironment {

    private int morningFoodQuantity;
    private final int dailyFoodQuantityModification;

    protected BasicEnvironmentImpl(final int xDimension, final int yDimension, final int morningFoodQuantity, final int dailyFoodQuantityModification) {
        super(xDimension, yDimension);
        this.morningFoodQuantity = morningFoodQuantity;
        this.dailyFoodQuantityModification = dailyFoodQuantityModification;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMorningFoodQuantity() {
        return morningFoodQuantity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextDay() {
        //deletes all current food in the environment
        super.clear();
        this.morningFoodQuantity -= this.dailyFoodQuantityModification;
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
