package model.environment;

/**
 * Models a factory for a BasicEnvironment.
 */
public interface BasicEnvironmentFactory {

    /**
     * Creates a BasicEnvironment.
     * @param xDimension
     *      the x-axis size of the Environment
     * @param yDimension
     *      the y-axis size of the Environment
     * @param morningFoodQuantity
     *      the food quantity for the first morning
     * @param dailyFoodQuantityModification
     *      how much will the quantity of food be modified every morning
     * @return a BasicEnviroment
     */
    BasicEnvironment createBasicEnviroment(int xDimension, int yDimension, int morningFoodQuantity, int dailyFoodQuantityModification);
}
