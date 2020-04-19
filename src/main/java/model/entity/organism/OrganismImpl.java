/**
 * 
 */
package model.entity.organism;

import java.util.EnumMap;

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

    private final EnumMap<TraitType, Trait> traits;
    private final OrganismEnvironmentHolder environmentKnowledge;

    /**
     * @param energy
     * organism energy.
     * @param traits
     * organism traits.
     * @param environmentKnowledge
     * organism knowledges about the environment.
     */
    protected OrganismImpl(final Energy energy, final EnumMap<TraitType, Trait> traits, final OrganismEnvironmentHolder environmentKnowledge) {
        super(energy);
        this.traits = traits;
        this.environmentKnowledge = environmentKnowledge;
    }

    @Override
    public final EnumMap<TraitType, Trait> getTraits() {
        return this.traits;
    }

    @Override
    public final OrganismEnvironmentHolder getEnvironmentKnowledge() {
        return this.environmentKnowledge;
    }

    @Override
    public final String toString() {
        return "Organism= [energy=" + this.getEnergy() + ", traits=" + this.getTraits() + "]";
    }

}
