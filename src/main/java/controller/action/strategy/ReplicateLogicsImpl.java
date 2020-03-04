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

    /**
     * {@inheritDoc}
     */
    @Override
    public Organism replicate(final Organism organism) {
        return duplicationFactory.createMutated(organism);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Energy computeConsumptionForReplication(final Organism organism) {
        return organism.getTraits().get(TraitType.CHILDRENQUANTITY).getFoodConsumption(organism);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfChild(final Organism organism) {
        return organism.getTraits().get(TraitType.CHILDRENQUANTITY).getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detractConsumptionForReplication(final Organism organism, final Energy energyToDetract) {
        organism.getEnergy().detractEnergy(energyToDetract);
    }
}
