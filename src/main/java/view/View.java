package view;

import java.util.Map.Entry;
import java.util.Set;

import controller.Controller;
import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;
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
     * Renders the simulation canvas.
     * @param foods
     *      foods in the environment
     * @param organisms
     *      organisms in the environment 
     */
    void render(Set<Entry<Position, Food>> foods, Set<Entry<Position, Organism>> organisms);

}
