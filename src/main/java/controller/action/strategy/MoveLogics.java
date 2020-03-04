/**
 * 
 */
package controller.action.strategy;

import model.entity.Energy;
import model.entity.organism.Organism;

/**
 * Interface that models the logic for a movement due to the Organism's traits.
 *
 */
public interface MoveLogics {

    /**
     * @param organism the Organism that will perform the movement
     * @return the Energy needed for movement
     */
    Energy computeConsumptionForMovement(Organism organism);

    /**
     * @return a random Direction
     */
    Direction getRandomDirection();

    /**
     * @param organism the Organism that will perform the movement
     */
    void detractConsumptionForMovement(Organism organism);
}
