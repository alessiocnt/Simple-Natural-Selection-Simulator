package model.enviroment;

/**
 * Represents a simple enviroment where every morning the quantity of spawned food will be modified by a constant amount.
 */
public interface BasicEnviroment extends Enviroment {

    /**
     * @return the quantity of food that spawns every morning
     */
    int getCurrentFoodQuantity();
}
