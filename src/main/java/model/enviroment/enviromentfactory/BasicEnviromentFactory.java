package model.enviroment.enviromentfactory;

import model.enviroment.BasicEnviroment;

/**
 * Models a factory for a BasicEnviroment.
 */
public interface BasicEnviromentFactory {

    /**
     * Creates a BasicEnviroment.
     * @param initialFoodQuantity
     *      the food quantity for the first day
     * @param dailyFoodModification
     *      how much will the quantity of food be modified everyday
     * @return a BasicEnviroment
     */
    BasicEnviroment createBasicEnviroment(int initialFoodQuantity, int dailyFoodModification);
}
