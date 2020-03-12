package model.environment;

import java.util.Set;

import model.entity.food.Food;
import model.entity.organism.Organism;

public interface AdvancedEnvironment extends BasicEnvironment {

    /**
     * @param organism
     *      the organism
     * @return
     *      a set of food containing the food nearby the given organism
     */
    Set<Food> getNearbyFoods(Organism organism);
}
