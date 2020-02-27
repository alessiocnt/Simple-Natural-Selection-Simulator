/**
 * 
 */
package controller.action.strategy;

import java.util.Optional;

import model.entity.food.Food;
import model.entity.organism.Organism;

/**
 * Interface that models the logic for eating a food.
 *
 */
public interface EatLogics {

    /**
     * @param organism the Organism that will eat
     * @param food the Food that will be eaten
     */
    void eat(Organism organism, Food food);

    /**
     * @param organism the Organism that should eat
     * @param food the Food that should be eaten
     * @return True if the Food can be eaten.
     *         False instead
     */
    boolean canEat(Organism organism, Optional<Food> food);

}
