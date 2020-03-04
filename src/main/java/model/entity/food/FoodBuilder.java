/**
 * 
 */
package model.entity.food;

import model.entity.Energy;

/**
 * Interface that models a food's builder.
 *
 */
public interface FoodBuilder {

    /**
     * Setter for the food energy value.
     * @param energy
     *      the food energy value
     * @return
     *      the FoodBuilder
     */
    FoodBuilder setEnergy(Energy energy);
    /**
     * @return an instance of OrganismImpl if all the fields are not null
     * @throws IllegalArgumentException instead
     */
    Food build();
}
