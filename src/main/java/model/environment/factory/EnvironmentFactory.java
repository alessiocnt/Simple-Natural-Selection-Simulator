package model.environment.factory;

import model.environment.AdvancedEnvironment;
import model.environment.BasicEnvironment;
import model.environment.temperature.Temperature;

/**
 * Models a factory for a BasicEnvironment.
 */
public interface EnvironmentFactory {

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
     * @param temperature
     *      the initial temperature of the environment
     * @return a AdvancedEnvironment
     */
    AdvancedEnvironment createAdvancedEnviroment(int xDimension, int yDimension, int morningFoodQuantity, int dailyFoodQuantityModification,
            Temperature temperature);
}
