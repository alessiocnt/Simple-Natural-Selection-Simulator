package model.environment;


public class BasicEnvironmentImpl extends AbstractEnvironment implements BasicEnvironment {

    private int morningFoodQuantity;
    private final int dailyFoodQuantityModification;

    public BasicEnvironmentImpl(final int xDimension, final int yDimension, final int morningFoodQuantity, final int dailyFoodQuantityModification) {
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
        for (int i = 0; i < this.getMorningFoodQuantity(); i++) {
            //TODO add a food from the food builder
            //super.addFood(/*HERE*/);
        }
    }
}
