package model.mutation.foodconsumption.strategy;

import model.entity.organism.Organism;

/**
 * Interface that models the strategy function used to get Food Consumption.
 */
public interface FoodConsumptionFunction {
    /**
     * @param organism
     * organism to get the food Consumption for this specific trait.
     * @return the food consumption.
     */
    int getConsumption(Organism organism);
}
