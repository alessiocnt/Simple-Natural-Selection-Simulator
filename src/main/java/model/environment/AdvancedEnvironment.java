package model.environment;

import java.util.Set;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.holders.OrganismEnvironmentHolder;

/**
 * Advanced Environment.
 *
 */
public interface AdvancedEnvironment extends BasicEnvironment, OrganismEnvironmentHolder {


    /**
     * @param organism
     *      the organism
     * @return
     *      a set of food containing the food nearby the given organism
     */
    Set<Food> getNearbyFoods(Organism organism);
}
