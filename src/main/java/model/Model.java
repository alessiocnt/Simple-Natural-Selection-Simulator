package model;

import java.util.Set;
import org.apache.commons.lang3.tuple.ImmutablePair;
import controller.action.ActionController;
import model.entity.food.Food;
import model.entity.food.FoodBuilder;
import model.entity.organism.Organism;
import model.environment.AdvancedEnvironment;
import model.environment.position.Position;
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

    /**
     * @return food in the environment.
     */
    Set<ImmutablePair<Position, Food>> getFoods();

    /**
     * @return organism in the environment.
     */
    Set<ImmutablePair<Position, Organism>> getOrganisms();

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
