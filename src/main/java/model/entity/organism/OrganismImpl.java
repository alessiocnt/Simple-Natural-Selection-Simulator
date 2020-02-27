/**
 * 
 */
package model.entity.organism;

import java.util.EnumMap;

import model.entity.Energy;
import model.entity.EntityImpl;
import model.environment.position.Position;
import model.mutation.Trait;
import model.mutation.TraitType;

/**
 * Class that models an Organism.
 *
 */
public class OrganismImpl extends EntityImpl implements Organism {

    private EnumMap<TraitType, Trait> traits;

    protected OrganismImpl(final Position position, final Energy energy, final EnumMap<TraitType, Trait> traits) {
        super(position, energy);
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
        return "Organism= [position=" + this.getPosition() + ", energy=" + this.getEnergy() + ", traits=" + this.getTraits() + "]";
    }

}
