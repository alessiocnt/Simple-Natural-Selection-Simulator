package model.environment;

import java.util.Iterator;
import java.util.Optional;

import model.entity.Position;
import model.entity.food.Food;
import model.entity.organism.Organism;

/**
 * Represent the enviroment in which Organisms and Food are contained.
 */
public interface Environment {
    /*/**
     * Add an organism to the enviroment.
     * @param organism
     *      the organism to add
     */
    void addOrganism(Organism organism);

    /**
     * Add a piece of food to the enviroment.
     * @param food
     *      the food piece to add.
     */
    void addFood(Food food);

    /**
     * Move an organism to a new position.
     * @param organism
     *      the organism to move
     * @param newPosition
     *      the position where the organism will be moved
     */
    void moveOrganism(Organism organism, Position newPosition);

    /**
     * Removes an organism from the enviroment.
     * @param organism
     *      the organism to remove
     */
    void removeOrganism(Organism organism);

    /**
     * Removes a food from the enviroment.
     * @param food
     *      the food piece to remove
     */
    void removeFood(Food food);

    /**
     * @return an iterator containing all the organisms inside the enviroment
     */
    Iterator<Organism> getOrganisms();

    /**
     * @param position
     *      the position of the organism required
     * @return an optional containing the organism or an empty one if the position was empty
     */
    Optional<Organism> getOrganism(Position position);

    /**
     * @param position
     *      the positon of the food required
     * @return an optional containing the food or an empty one if the position was empty
     */
    Optional<Food> getFood(Position position);

    /**
     * Removes every entity from the enviroment.
     */
    void clear();
}
