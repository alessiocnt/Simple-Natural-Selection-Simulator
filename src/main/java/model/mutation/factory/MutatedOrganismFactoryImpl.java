package model.mutation.factory;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import model.entity.EnergyImpl;
import model.entity.organism.Organism;
import model.entity.organism.OrganismBuilder;
import model.entity.organism.OrganismBuilderImpl;
import model.mutation.ChildrenQuantity;
import model.mutation.Dimension;
import model.mutation.MutationRarity;
import model.mutation.Speed;
import model.mutation.Trait;
import model.mutation.TraitType;

/**
 * Children factory.
 */
public class MutatedOrganismFactoryImpl implements MutatedOrganismFactory {

    @Override
    public final Organism createMutated(final Organism organism) {
        Objects.requireNonNull(organism);
        Map<TraitType, Trait> traits = organism.getTraits();
        Map<TraitType, Trait> mutatedTraits = traits.entrySet().stream()
                                    .filter((entrySet) -> !entrySet.getValue().getRarity().equals(MutationRarity.NOMUTATION))
                                    .collect(Collectors.toMap((entrySet) -> entrySet.getKey(),
                                                (entrySet) -> this.getMutatedTrait(entrySet.getKey(), entrySet.getValue().getValue())));
        //Child has the same energy of dad.
        final OrganismBuilder organismBuilder = new OrganismBuilderImpl(new EnergyImpl(organism.getEnergy().getEnergy()));
        mutatedTraits.entrySet().forEach((entrySet) -> organismBuilder.trait(entrySet.getKey(), entrySet.getValue()));
        Organism mutatedOrganism = organismBuilder.build();
        return mutatedOrganism;
    }

    private Trait getMutatedTrait(final TraitType type, final int value) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(value);
        Trait newTrait = null;
        final Random rnd = new Random();
        int newValue = value;
        if (rnd.nextDouble() < type.getRarity().getPercentage()) {
            final double variationPercentage = rnd.nextDouble() / (value > 1 ? 2 : 1);
            //If the random number is lower than the rarity percentage, the trait mutate.
            do {
                if (rnd.nextBoolean()) {
                    //If true the sign is positive.
                    newValue = (int) Math.round(value + value * variationPercentage);
                } else {
                    //If false the sign is negative.
                    newValue = (int) Math.round(value - value * variationPercentage);
                }
            } while (newValue < type.getValues().getStart() || newValue > type.getValues().getStop());

        }
        switch (type) {
            case SPEED:
                    newTrait = new Speed(newValue);
                break;
            case DIMENSION:
                    newTrait = new Dimension(newValue);
                break;
            case CHILDRENQUANTITY:
                    newTrait = new ChildrenQuantity(newValue);
                break;
            default:
                    /* If no one can handle the request instead of returning null, 
                    *  it throw a runtime exception. */
                    throw new IllegalArgumentException();
        }
        return newTrait;
    }
}
