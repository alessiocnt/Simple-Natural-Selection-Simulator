package model.mutation.factory;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import model.entity.EnergyImpl;
import model.entity.organism.Organism;
import model.entity.organism.OrganismBuilder;
import model.entity.organism.OrganismBuilderImpl;
import model.mutation.MutationRarity;
import model.mutation.TraitType;
import model.mutation.trait.ChildrenQuantity;
import model.mutation.trait.Dimension;
import model.mutation.trait.FoodRadar;
import model.mutation.trait.Speed;
import model.mutation.trait.Trait;

/**
 * Children factory.
 */
public class MutatedOrganismFactoryImpl implements MutatedOrganismFactory {

    @Override
    public final Organism createMutated(final Organism organism) {
        Objects.requireNonNull(organism);
        final Map<TraitType, Trait> traits = organism.getTraits();
        final Map<TraitType, Trait> mutatedTraits = traits.entrySet().stream()
                                    .filter((entrySet) -> !entrySet.getValue().getType().getRarity().equals(MutationRarity.NOMUTATION))
                                    .collect(Collectors.toMap((entrySet) -> entrySet.getKey(),
                                                              (entrySet) -> this.getMutatedTrait(entrySet.getKey(), entrySet.getValue().getValue())));
        //Child has the same energy of dad.
        final OrganismBuilder organismBuilder = new OrganismBuilderImpl(new EnergyImpl(organism.getEnergy().getEnergy()))
                                                    .setEnvironmentKnowledge(organism.getEnvironmentKnowledge());
        //Insert mutate trait.
        mutatedTraits.entrySet().forEach((entrySet) -> organismBuilder.setTrait(entrySet.getKey(), entrySet.getValue()));
        //Insert also not mutable trait in children, copying value from dad.
        traits.entrySet().stream()
            .filter((x) -> x.getKey().getRarity().equals(MutationRarity.NOMUTATION))
            .forEach((x) -> organismBuilder.setTrait(x.getKey(), x.getValue()));
        final Organism mutatedOrganism = organismBuilder.build();
        return mutatedOrganism;
    }

    private Trait getMutatedTrait(final TraitType type, final int value) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(value);
        final Trait newTrait;
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
            case FOODRADAR:
                    newTrait = new FoodRadar(newValue);
                break;
            default:
                    /* If no one can handle the request instead of returning null, 
                    *  it throw a runtime exception. */
                    throw new IllegalArgumentException();
        }
        return newTrait;
    }
}
