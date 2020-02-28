/**
 * 
 */
package controller.action;

import controller.action.strategy.ReplicateLogics;
import controller.action.strategy.ReplicateLogicsImpl;
import model.entity.Energy;
import model.entity.organism.Organism;
import model.environment.Environment;
import model.environment.daycicle.DayPeriod;

/**
 * This class performs all the actions that occur in a night-time.
 *
 */
public class NightAction extends AbstractAction {

    private final ReplicateLogics replicateLogic;
    private final Environment environment;

    public NightAction(final Environment environment) {
        super(DayPeriod.NIGHT);
        this.environment = environment;
        this.replicateLogic = new ReplicateLogicsImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(final Organism organism) {
        if (!tryToRemoveOrganism(organism)) {
            for (int i = 0; i < replicateLogic.getNumberOfChild(organism); i++) {
                environment.addOrganism(organism, replicateLogic.replicate(organism));
            }
            replicateLogic.detractConsumptionForReplication(organism, replicateLogic.computeConsumptionForReplication(organism));
        }
    }

    /**
     * @param organism the Organism
     * @return True if the Energy is enough for replication
     *         False instead.
     */
    private boolean isEnergyEnoughtToReplicate(final Organism organism) {
        return Energy.greater(organism.getEnergy(), replicateLogic.computeConsumptionForReplication(organism))
                || organism.getEnergy().equals(replicateLogic.computeConsumptionForReplication(organism));
    }

    /**
     * @param organism the Organism to try to remove
     * @return True if the Organism is removed.
     *         False instead.
     */
    private boolean tryToRemoveOrganism(final Organism organism) {
        if (!isEnergyEnoughtToReplicate(organism)) {
            environment.removeOrganism(organism);
            return true;
        }
        return false;
    }
}
