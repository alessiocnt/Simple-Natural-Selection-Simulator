package model.mutation.foodconsumption.strategy;

import model.entity.organism.Organism;

/**
 * Function used to get Food Consuption.
 */
public interface FoodConsumptionFunc {
    /**
     * @param organism
     * entity to get the food Consumption for this specific trait.
     * @return the food consuption.
     */
    int getConsumption(Organism organism);
}
