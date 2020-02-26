package model.mutation;

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
     * @return the food consuption of the trait.
     */
    int getFoodConsuption();
    /**
     * @return the mutation rarity of the trait.
     */
    MutationRarity getRarity();
}
