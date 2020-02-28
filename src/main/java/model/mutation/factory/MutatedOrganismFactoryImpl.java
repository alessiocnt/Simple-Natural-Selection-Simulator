package model.mutation.factory;

import java.util.Map;
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
        Map<TraitType, Trait> traits = organism.getTraits();
        Map<TraitType, Trait> mutatedTraits = traits.entrySet().stream()
                                    .filter((entrySet) -> !entrySet.getValue().getRarity().equals(MutationRarity.NOMUTATION))
                                    .collect(Collectors.toMap((entrySet) -> entrySet.getKey(),
                                    (entrySet) -> this.getMutatedTrait(entrySet.getKey(), entrySet.getValue().getValue())));
        final OrganismBuilder organismBuilder = new OrganismBuilderImpl(
                                    new EnergyImpl(mutatedTraits.getOrDefault(TraitType.DIMENSION, new Dimension(100)).getValue()));
        mutatedTraits.entrySet().forEach((entrySet) -> organismBuilder.trait(entrySet.getKey(), entrySet.getValue()));
        Organism mutatedOrganism = organismBuilder.build();
        return mutatedOrganism;
    }
    //MAIN DI PROVA
    public static void main(String...strings ) {
        OrganismBuilder organismBuilder = new OrganismBuilderImpl(new EnergyImpl(100));
        organismBuilder.trait(TraitType.SPEED, new Speed(5));
        organismBuilder.trait(TraitType.DIMENSION, new Dimension(100));
        organismBuilder.trait(TraitType.CHILDRENQUANTITY, new ChildrenQuantity(1));
        Organism x = organismBuilder.build();
        MutatedOrganismFactory factory = new MutatedOrganismFactoryImpl();
        Organism children = factory.createMutated(x);
        if (x != children) {
            System.out.println(x);
            System.out.println(children);
        }
    }

    private Trait getMutatedTrait(final TraitType type, final int value) {
        Trait newTrait = null;
        final Random rnd = new Random();
        int newValue = value;
        if (rnd.nextDouble() < type.getRarity().getPercentage()) {
            final double variationPercentage = rnd.nextDouble() / (value > 1 ? 2 : 1);
            //Se il numero estratto é minore della percentuale allora significa che muto
            do {
                System.out.println("Provo" + type);
                if (rnd.nextBoolean()) {
                    //Se é true segno positivo
                    newValue = (int) Math.round(value + value * variationPercentage);
                } else {
                    //Segno negativo
                    newValue = (int) Math.round(value - value * variationPercentage);
                }
            } while (newValue <= 0);

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
                    throw new IllegalArgumentException();
        }
        return newTrait;
    }
}
