/**
 * 
 */
package model.entity.organism;

import java.util.EnumMap;

import model.entity.Energy;
import model.environment.position.Position;
import model.mutation.Trait;
import model.mutation.TraitType;

/**
 * Class that models an organism's builder.
 *
 */
public class OrganismBuilderImpl implements OrganismBuilder {

    private static final String EXCEPTIONMESSAGE = "Argument can not be null.";

    private Energy energy;
    private EnumMap<TraitType, Trait> traits;

    public OrganismBuilderImpl(final Energy energy) {
        this.energy = energy;
        this.traits = new EnumMap<TraitType, Trait>(TraitType.class);
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
            return new OrganismImpl(this.energy, this.traits);
        }
        throw new IllegalArgumentException(EXCEPTIONMESSAGE);
    }
}
