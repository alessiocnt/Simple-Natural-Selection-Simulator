/**
 * 
 */
package model.entity.organism;

import java.util.EnumMap;

import model.entity.Energy;
import model.entity.EnergyImpl;
import model.mutation.Trait;
import model.mutation.TraitType;

/**
 * Class that models an organism's builder.
 *
 */
public class OrganismBuilderImpl implements OrganismBuilder {

    private static final String EXCEPTIONMESSAGE = "Argument can not be null.";

    private final Energy energy;
    private EnumMap<TraitType, Trait> traits;

    /**
     * @param energy
     * organism energy.
     */
    public OrganismBuilderImpl(final Energy energy) {
        this.energy = energy;
        this.traits = new EnumMap<>(TraitType.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrganismBuilderImpl trait(final TraitType type, final Trait trait) {
        this.traits.put(type, trait);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrganismImpl build() {
        if (this.energy != null && !this.traits.isEmpty()) {
            return new OrganismImpl(new EnergyImpl(this.energy.getEnergy()), this.traits);
        }
        throw new IllegalArgumentException(EXCEPTIONMESSAGE);
    }
}
