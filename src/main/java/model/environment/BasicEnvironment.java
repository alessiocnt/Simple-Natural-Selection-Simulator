package model.environment;

/**
 * Represents a simple enviroment where every morning the quantity of spawned food will be modified by a constant amount.
 */
public interface BasicEnvironment extends Environment {

    /**
     * @return the quantity of food that spawns every morning
     */
    int getCurrentFoodQuantity();
}
