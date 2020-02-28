package model.mutation;

import java.util.function.Predicate;

import model.entity.Energy;
import model.entity.EnergyImpl;
import model.entity.organism.Organism;
import model.mutation.foodconsumption.strategy.FoodConsumptionFunc;
import model.mutation.utilities.CheckUtil;

/**
 * Abstract class for trait.
 */
public abstract class AbstractTrait implements Trait {

    private final int value;
    private final FoodConsumptionFunc foodConsumption;

    /*
     * @param value
     * Value of the trait.
     * @param foodConsumption
     * the strategy for calculate the foodConsumption.
     * @param checkValue
     * the supplier, for controlling the validity of the value.
     * 
     */
    AbstractTrait(final int value, final FoodConsumptionFunc foodConsumption, final Predicate<Integer> checkValue) {
        CheckUtil.check(() -> checkValue.test(value), new IllegalArgumentException());
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
