package model.mutation;

import model.entity.organism.Organism;

/**
 * Interface that describes an entity trait.
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
     * organism.
     */
    int getFoodConsumption(Organism organism);
    /**
     * @return the mutation rarity of the trait.
     */
    MutationRarity getRarity();
}
