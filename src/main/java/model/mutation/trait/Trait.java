package model.mutation.trait;

import model.entity.Energy;
import model.entity.organism.Organism;
import model.mutation.TraitType;

/**
 * Interface that describes an organism trait.
 *
 */
public interface Trait  {
    /**
     * @return the value that describe the trait.
     * 
     */
    int getValue();

    /**
     * @return the food consumption of the trait.
     * @param organism
     * organism to get the food consumption.
     */
    Energy getFoodConsumption(Organism organism);

    /**
     * @return the type of the trait.
     */
    TraitType getType();
}
