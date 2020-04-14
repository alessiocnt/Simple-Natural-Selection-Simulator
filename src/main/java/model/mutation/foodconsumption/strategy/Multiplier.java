package model.mutation.foodconsumption.strategy;

import model.entity.organism.Organism;

/**
 * Strategy for calculate the food consumption based on a moltiplication.
 */
public class Multiplier implements FoodConsumptionFunction {

    private final double factor;
    private final int value;

    /**
     * @param value
     * Value of trait.
     * @param factor
     * Factor of moltiplication.
     */
    public Multiplier(final int value, final double factor) {
        this.value = value;
        this.factor = factor;
    }

    @Override
    public final int getConsumption(final Organism organism) {
        return ((Double) (this.value * this.factor)).intValue();
    }

}
