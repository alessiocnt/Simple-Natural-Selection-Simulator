package model.environment.environmentfactory;

import model.environment.BasicEnvironment;

/**
 * Models a factory for a BasicEnviroment.
 */
public interface BasicEnvironmentFactory {

    /**
     * Creates a BasicEnviroment.
     * @param initialFoodQuantity
     *      the food quantity for the first day
     * @param dailyFoodModification
     *      how much will the quantity of food be modified everyday
     * @return a BasicEnviroment
     */
    BasicEnvironment createBasicEnviroment(int initialFoodQuantity, int dailyFoodModification);
}
