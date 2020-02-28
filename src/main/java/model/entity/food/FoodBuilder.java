/**
 * 
 */
package model.entity.food;

/**
 * Interface that models a food's builder.
 *
 */
public interface FoodBuilder {

    /**
     * @return an instance of OrganismImpl if all the fields are not null
     * @throws IllegalArgumentException instead
     */
    Food build();
}
