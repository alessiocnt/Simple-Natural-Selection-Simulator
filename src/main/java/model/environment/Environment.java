package model.environment;

import java.util.Iterator;
import java.util.Optional;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;

/**
 * Represent the environment in which Organisms and Food are contained.
 */
public interface Environment {
    /**
     * Add an organism to the environment.
     * @param organism
     *      the organism to add
     */
    void addOrganism(Organism organism);

    /**
     * Add an organism in the father position.
     * @param father
     *      the father organism
     * @param son
     *      the new organisms
     */
    void addOrganism(Organism father, Organism...son);

    /**
     * Add a piece of food to the environment.
     * @param food
     *      the food piece to add.
     */
    void addFood(Food food);

    /**
     * Move an organism to a new position.
     * @param organism
     *      the organism to move
     * @param xOffset
     *      the offset of the x-axis
     * @param yOffset
     *      the offset of the y-axis
     * @throws OutOfEnviromentException
     *      if the Organism tries to exit the Environment 
     */
    void moveOrganism(Organism organism, int xOffset, int yOffset) throws OutOfEnviromentException;

    /**
     * Removes an organism from the environment.
     * @param organism
     *      the organism to remove
     */
    void removeOrganism(Organism organism);

    /**
     * Removes a food from the environment.
     * @param food
     *      the food piece to remove
     */
    void removeFood(Food food);

    /**
     * @return an iterator containing all the organisms inside the environment
     */
    Iterator<Organism> getOrganisms();

    /**
     * @param position
     *      the position of the organism required
     * @return an optional containing the organism or an empty one if the position was empty
     */
    Optional<Organism> getOrganism(Position position);

    /**
     * @param organism
     *      the organism that requires the food piece
     * @return an optional containing the food or an empty one if the position was empty
     */
    Optional<Food> getFood(Organism organism);

    /**
     * @param position
     *      the position of the food required
     * @return an optional containing the food or an empty one if the position was empty
     */
    Optional<Food> getFood(Position position);

    /**
     * Removes every entity from the environment.
     */
    void clear();
}
