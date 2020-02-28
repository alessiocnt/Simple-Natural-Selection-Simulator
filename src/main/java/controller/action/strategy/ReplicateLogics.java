/**
 * 
 */
package controller.action.strategy;

import model.entity.Energy;
import model.entity.organism.Organism;

/**
 * Interface that models the logic for replicating an Organism.
 *
 */
public interface ReplicateLogics {

    /**
     * @param organism the Organism that will replicate
     * @return the Energy needed for replication
     */
    Energy computeConsumptionForReplication(Organism organism);

    /**
     * @param organism the Organism that will replicate
     * @return the son of the Organism
     */
    Organism replicate(Organism organism);

    /**
     * @param organism the Organism that will replicate
     * @return the son's number of the Organism after the replication
     */
    int getNumberOfChild(Organism organism);

    /**
     * @param organism the Organism that will perform the movement
     * @param energyToDetract the energy value that will be spent to perform the movement
     */
    void detractConsumptionForReplication(Organism organism, Energy energyToDetract);
}
