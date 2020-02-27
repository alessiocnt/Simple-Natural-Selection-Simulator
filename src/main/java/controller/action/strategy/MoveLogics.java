/**
 * 
 */
package controller.action.strategy;

import model.entity.Energy;
import model.entity.organism.Organism;
import utilities.Pair;

/**
 * Interface that models the logic for a movement due to the Organism's traits.
 *
 */
public interface MoveLogics {

    /**
     * @param organism an EnumMap with all the Trait proper to the Organism
     * @return a Pair representing the vector of the movement the Organism should perform
     */
    Pair<Integer, Integer> calculateVectorDirection(Organism organism);

    /**
     * @return a Pair representing a none direction. The Organism's position will not vary
     */
    Pair<Integer, Integer> getZeroDirectionVector();

    /**
     * @param organism the Organism that will perform the movement
     * @return the Energy needed for movement
     */
    Energy computeConsumptionForMovement(Organism organism);
}
