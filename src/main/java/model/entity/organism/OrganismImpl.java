/**
 * 
 */
package model.entity.organism;

import java.util.EnumMap;

import model.entity.Energy;
import model.entity.AbstractEntity;
import model.mutation.Trait;
import model.mutation.TraitType;

/**
 * Class that models an Organism.
 *
 */
public class OrganismImpl extends AbstractEntity implements Organism {

    private EnumMap<TraitType, Trait> traits;

    protected OrganismImpl(final Energy energy, final EnumMap<TraitType, Trait> traits) {
        super(energy);
        this.traits = traits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumMap<TraitType, Trait> getTraits() {
        return this.traits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Organism= [energy=" + this.getEnergy() + ", traits=" + this.getTraits() + "]";
    }

}
