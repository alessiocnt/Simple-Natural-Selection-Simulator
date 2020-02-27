package model.mutation;

import model.entity.organism.Organism;

/**
 * Function used to get Food Consuption.
 */
public interface FoodConsuptionFunc {
    /**
     * @param entity
     * entity to get the food Consumption for this specific trait.
     * @return the food consuption.
     */
    int getConsumption(Organism entity);
}
