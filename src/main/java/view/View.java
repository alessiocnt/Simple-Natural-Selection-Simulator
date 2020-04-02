package view;

import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;

import controller.Controller;
import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;
import view.scenecontroller.simulationstrategy.SimulationHandler;
import view.scenefactory.SceneFactory;

/**
 * View interface.
 *
 */
public interface View {
    /**
     * @param controller
     * controller of the simulator.
     */
    void launch(Controller controller);

    /**
     * @return the controller.
     */
    Controller getController();

    /**
     * @return the current sceneFactory.
     */
    SceneFactory getSceneFactory();

    /**
     * Sets the simulation controller.
     * @param simulationController
     *      the SimulationController
     */
    void setSimulationController(SimulationHandler simulationController);

    /**
     * Tells view that the simulation is over.
     */
    void setSimulationOver();

    /**
     * Renders the simulation canvas.
     * @param foods
     *      foods in the environment
     * @param organisms
     *      organisms in the environment 
     */
    void render(Set<ImmutablePair<Position, Food>> foods, Set<ImmutablePair<Position, Organism>> organisms);

}
