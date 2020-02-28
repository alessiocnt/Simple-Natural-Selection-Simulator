package model.mutation;

import model.entity.Energy;
import model.entity.EnergyImpl;
import model.entity.organism.Organism;

/**
 * Abstract class for trait.
 */
public abstract class AbstractTrait implements Trait {

    private final int value;
    private final FoodConsumptionFunc foodConsumption;

    AbstractTrait(final int value, final FoodConsumptionFunc foodConsumption) {
        this.value = value;
        this.foodConsumption = foodConsumption;
    }

    @Override
    public final int getValue() {
        return this.value;
    }

    @Override
    public final Energy getFoodConsumption(final Organism organism) {
        return new EnergyImpl(this.foodConsumption.getConsumption(organism));
    }

    @Override
    public final String toString() {
        return "Value: " + this.getValue() + ", Rarity: " + this.getRarity();
    }
}
