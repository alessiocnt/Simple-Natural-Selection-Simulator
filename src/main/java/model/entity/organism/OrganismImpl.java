/**
 * 
 */
package model.entity.organism;

import java.util.Map;
import model.entity.Energy;
import model.environment.holders.OrganismEnvironmentHolder;
import model.entity.AbstractEntity;
import model.mutation.TraitType;
import model.mutation.trait.Trait;

/**
 * Class that models an Organism.
 *
 */
public class OrganismImpl extends AbstractEntity implements Organism {

    private final Map<TraitType, Trait> traits;
    private final OrganismEnvironmentHolder environmentKnowledge;

    /**
     * @param energy
     * organism energy.
     * @param traits
     * organism traits.
     * @param environmentKnowledge
     * organism knowledges about the environment.
     */
    protected OrganismImpl(final Energy energy, final Map<TraitType, Trait> traits, final OrganismEnvironmentHolder environmentKnowledge) {
        super(energy);
        this.traits = traits;
        this.environmentKnowledge = environmentKnowledge;
    }

    @Override
    public final Map<TraitType, Trait> getTraits() {
        return this.traits;
    }

    @Override
    public final OrganismEnvironmentHolder getEnvironmentKnowledge() {
        return this.environmentKnowledge;
    }

    /**
     * toString for an Organism.
     */
    @Override
    public String toString() {
        return "Organism= [energy=" + this.getEnergy() + ", traits=" + this.getTraits() + "]";
    }

}
