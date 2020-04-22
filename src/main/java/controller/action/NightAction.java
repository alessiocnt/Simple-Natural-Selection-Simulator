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

    /**
     * @param environment
     * the environment to control.
     */
    public NightAction(final Environment environment) {
        super(DayPeriod.NIGHT);
        this.environment = environment;
        this.replicateLogic = new ReplicateLogicsImpl();
    }

    @Override
    public final void perform(final Organism organism) {
        if (!this.tryToRemoveOrganism(organism)) {
            for (int i = 0; i < this.replicateLogic.getNumberOfChild(organism); i++) {
                this.environment.addOrganism(organism, this.replicateLogic.replicate(organism));
            }
            this.replicateLogic.detractConsumptionForReplication(organism);
        }
    }

    /**
     * @param organism the Organism
     * @return True if the Energy is enough for replication
     *         False instead.
     */
    private boolean isEnergyEnoughtToReplicate(final Organism organism) {
        return Energy.greater(organism.getEnergy(), this.replicateLogic.computeConsumptionForReplication(organism))
                || organism.getEnergy().equals(this.replicateLogic.computeConsumptionForReplication(organism));
    }

    /**
     * @param organism the Organism to try to remove
     * @return True if the Organism is removed.
     *         False instead.
     */
    private boolean tryToRemoveOrganism(final Organism organism) {
        if (!this.isEnergyEnoughtToReplicate(organism)) {
            this.environment.removeOrganism(organism);
            return true;
        }
        return false;
    }
}
