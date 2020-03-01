package view.scenecontroller.simulationstrategy;

import java.util.Set;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;
import utilities.Pair;

/**
 * Interface that models the logic to display entities on screen.
 */
public interface SimulationViewLogics {

    /**
     * Sets the entities to display.
     * @param foods
     *      foods that will be displayed
     * @param organisms
     *      organisms that will be displayed
     */
    void setEntities(Set<Pair<Position, Food>> foods, Set<Pair<Position, Organism>> organisms);

    /**
     * Updates the canvas.
     */
    void update();

    /**
     * Tells logics what is the canvas dimensions.
     * @param width
     *      the canvas width
     * @param height
     *      the canvas height
     */
    void setCanvasDimension(double width, double height);
}
