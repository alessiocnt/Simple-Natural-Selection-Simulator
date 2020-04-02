package view;

import java.util.Set;

import controller.Controller;
import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;
import utilities.Pair;
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
    void render(Set<Pair<Position, Food>> foods, Set<Pair<Position, Organism>> organisms);

}
