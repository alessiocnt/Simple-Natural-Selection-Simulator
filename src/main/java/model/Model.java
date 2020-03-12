package model;

import java.util.Set;

import controller.action.ActionController;
import model.entity.food.Food;
import model.entity.food.FoodBuilder;
import model.entity.organism.Organism;
import model.environment.AdvancedEnvironment;
import model.environment.OrganismEnvironmentHolder;
import model.environment.position.Position;
import utilities.Pair;
import view.entities.EnvironmentHolder;

/**
 * Model interface.
 *
 */
public interface Model {

    /**
     * @return the environment
     */
    AdvancedEnvironment getEnvironment();

    /**
     * @return the foodBuilder
     */
    FoodBuilder getFoodBuilder();

    /**
     * @return the actionController
     */
    ActionController getActionController();

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
     * @return the environment holder for the organism use
     */
    OrganismEnvironmentHolder getOrganismEnvironmentHolder();

    /**
     * Maintains the data needed for the Environment to be created.
     * @param holder
     *      the data holder
     */
    void prepareEnvironment(EnvironmentHolder holder);

}
