package model.environment;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.exceptions.OutOfEnviromentException;
import model.environment.position.Position;

/**
 * Represent the environment in which Organisms and Food are contained.
 */
public interface Environment {

    /**
     * @return the number of organisms inside the Environment
     */
    int getCurrendOrganismQuantity();

    /**
     * @return the quantity of food inside the Environment
     */
    int getCurrentFoodQuantity();

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
     * @throws NoSuchElementException
     *      if the father doesn't exists
     */
    void addOrganism(Organism father, Organism...son) throws NoSuchElementException;

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
     * @throws NoSuchElementException
     *      if the organism doesn't exists
     */
    void moveOrganism(Organism organism, int xOffset, int yOffset) throws OutOfEnviromentException, NoSuchElementException;

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
     *      the position of the organisms required
     * @return an optional containing a set of the organisms or an empty one if the position was empty
     */
    Optional<Set<Organism>> getOrganisms(Position position);

    /**
     * @param organism
     *      the organism that requires the food piece
     * @return an optional containing the food or an empty one if the position was empty
     * @throws NoSuchElementException
     *      if the Organism doesn't exists 
     */
    Optional<Food> getFood(Organism organism) throws NoSuchElementException;

    /**
     * @param position
     *      the position of the food required
     * @return an optional containing the food or an empty one if the position was empty
     */
    Optional<Food> getFood(Position position);

    /**
     * Removes every food piece from the environment.
     */
    void clear();
}
