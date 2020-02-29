package model;

import java.util.Map.Entry;
import java.util.Set;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.daycicle.DayCicle;
import model.environment.position.Position;

/**
 * Model interface.
 *
 */
public interface Model {

    /**
     * Updates all the objects in the simulation.
     * @param dayCicle
     *      the controller of day/night in the simulation
     */
    void update(DayCicle dayCicle);

    /**
     * @return true if the simulation is over
     */
    boolean isSimulationOver();

    Set<Entry<Position, Food>> getFoods();

    Set<Entry<Position, Organism>> getOrganisms();
}
