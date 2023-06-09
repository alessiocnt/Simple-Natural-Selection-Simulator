/**
 * 
 */
package model.entity.organism;

import java.util.EnumMap;
import java.util.Optional;

import model.entity.Energy;
import model.entity.EnergyImpl;
import model.environment.holders.OrganismEnvironmentHolder;
import model.mutation.TraitType;
import model.mutation.trait.Trait;

/**
 * Class that models an organism's builder.
 *
 */
public class OrganismBuilderImpl implements OrganismBuilder {

    private static final String EXCEPTIONMESSAGE = "Argument can not be null.";

    private final Energy energy;
    private final EnumMap<TraitType, Trait> traits;
    private Optional<OrganismEnvironmentHolder> environmentKnowledge;

    /**
     * @param energy
     * organism energy.
     */
    public OrganismBuilderImpl(final Energy energy) {
        this.energy = energy;
        this.traits = new EnumMap<>(TraitType.class);
        this.environmentKnowledge = Optional.empty();
    }

    @Override
    public final OrganismBuilderImpl setTrait(final Trait trait) {
        this.traits.put(trait.getType(), trait);
        return this;
    }

    @Override
    public final OrganismBuilderImpl setEnvironmentKnowledge(final OrganismEnvironmentHolder environmentKnowledge) {
        this.environmentKnowledge = Optional.of(environmentKnowledge);
        return this;
    }

    @Override
    public final Organism build() {
        if (this.energy != null && !this.traits.isEmpty() && this.environmentKnowledge.isPresent()) {
            return new OrganismImpl(new EnergyImpl(this.energy.getEnergy()), this.traits, this.environmentKnowledge.get());
        }
        throw new IllegalArgumentException(EXCEPTIONMESSAGE);
    }

}
