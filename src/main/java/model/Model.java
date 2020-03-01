package model;

import java.util.Set;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.daycicle.DayCicle;
import model.environment.position.Position;
import utilities.Pair;
import view.entities.EnvironmentHolder;

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

    Set<Pair<Position, Food>> getFoods();

    Set<Pair<Position, Organism>> getOrganisms();

    /**
     * @return the environment dimension
     */
    Position getEnvironmentDimension();

    /**
     * Maintains the data needed for the Environment to be created.
     * @param holder
     *      the data holder
     */
    void prepareEnvironment(EnvironmentHolder holder);
}
