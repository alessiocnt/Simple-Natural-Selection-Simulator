package model.mutation;

/**
 * Interface that describes an entity trait.
 * @param <X> 
 * type of value described in the trait.
 *
 */
public interface Trait<X> {
    /**
     * @return the value that describe the trait.
     */
    X getValue();
    /**
     * @return the food consuption of the trait.
     */
    int getFoodConsuption();
    /**
     * @return the mutation rarity of the trait.
     */
    MutationRarity getRarity();
}
