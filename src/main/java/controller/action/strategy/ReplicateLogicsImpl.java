/**
 * 
 */
package controller.action.strategy;

import model.entity.Energy;
import model.entity.organism.Organism;
import model.mutation.TraitType;
import model.mutation.factory.MutatedOrganismFactory;
import model.mutation.factory.MutatedOrganismFactoryImpl;

/**
 * Class that defines the logic for for replicating an Organism.
 *
 */
public class ReplicateLogicsImpl implements ReplicateLogics {

    private final MutatedOrganismFactory duplicationFactory;

    /**
     * Constructor.
     */
    public ReplicateLogicsImpl() {
        this.duplicationFactory = new MutatedOrganismFactoryImpl();
    }

    @Override
    public final Organism replicate(final Organism organism) {
        return duplicationFactory.createMutated(organism);
    }

    @Override
    public final Energy computeConsumptionForReplication(final Organism organism) {
        return organism.getTraits().get(TraitType.CHILDRENQUANTITY).getFoodConsumption(organism);
    }

    @Override
    public final int getNumberOfChild(final Organism organism) {
        return organism.getTraits().get(TraitType.CHILDRENQUANTITY).getValue();
    }

    @Override
    public final void detractConsumptionForReplication(final Organism organism) {
        Energy energyToDetract = computeConsumptionForReplication(organism);
        organism.getEnergy().detractEnergy(energyToDetract);
    }
}
