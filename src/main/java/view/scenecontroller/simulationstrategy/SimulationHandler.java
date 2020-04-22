package view.scenecontroller.simulationstrategy;

import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;
import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;

/**
 * Interface that enable render and stopSimulation, 
 * so enable the handle of simulation.
 */
public interface SimulationHandler {

    /**
     * Updates the canvas with Environment parameters.
     * 
     * @param foods
     * food that will be displayed
     * @param organisms
     * organisms that will be displayed
     */
    void render(Set<ImmutablePair<Position, Food>> foods, Set<ImmutablePair<Position, Organism>> organisms);

    /**
     * Tells SimulationController that the simulation is over.
     */
    void simulationOver();
}
