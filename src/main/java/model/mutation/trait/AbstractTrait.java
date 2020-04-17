package model.mutation.trait;


import java.util.Objects;

import model.entity.Energy;
import model.entity.EnergyImpl;
import model.entity.organism.Organism;
import model.mutation.TraitType;
import model.mutation.foodconsumption.strategy.FoodConsumptionFunction;
import model.mutation.utilities.CheckUtil;

/**
 * Abstract class for trait.
 */
public abstract class AbstractTrait implements Trait {

    private final int value;
    private final FoodConsumptionFunction foodConsumption;
    private final TraitType type;

    /*
     * @param value
     * Value of the trait.
     * @param foodConsumption
     * the strategy for calculating the food consumption.
     */
    AbstractTrait(final int value, final FoodConsumptionFunction foodConsumption, final TraitType type) {
        Objects.requireNonNull(value, "Value must not be null");
        Objects.requireNonNull(foodConsumption, "Food Consumption function must not be null");
        Objects.requireNonNull(type, "Trait type must not be null");
        //Check that the value is in the correct range.
        CheckUtil.check(() -> value >= type.getValues().getStart() && value <= type.getValues().getStop(), new IllegalArgumentException());
        this.value = value;
        this.foodConsumption = foodConsumption;
        this.type = type;
    }

    /*
     * Get value
     */
    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public final Energy getFoodConsumption(final Organism organism) {
        return new EnergyImpl(this.foodConsumption.getConsumption(organism));
    }

    @Override
    public final String toString() {
        return "Value: " + this.getValue() + ", Rarity: " + this.type.getRarity();
    }

    @Override
    public final TraitType getType() {
        return this.type;
    }
}
