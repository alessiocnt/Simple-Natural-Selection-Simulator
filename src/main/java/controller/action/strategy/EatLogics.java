/**
 * 
 */
package controller.action.strategy;

import java.util.Set;

import model.entity.food.Food;
import model.entity.organism.Organism;

/**
 * Interface that models the logic for eating a food.
 *
 */
public interface EatLogics {

    /**
     * @param organism the Organism that will eat
     * @param foods a Set with the Food that will be eaten
     * Eats the food.
     */
    void eat(Organism organism, Set<Food> foods);

    /**
     * @param organism the Organism that should eat
     * @param foods a Set with Food that should be eaten
     * @return True if the Food can be eaten.
     *         False instead
     */
    boolean canEat(Organism organism, Set<Food> foods);

}
