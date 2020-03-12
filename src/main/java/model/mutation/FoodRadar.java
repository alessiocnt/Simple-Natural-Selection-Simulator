package model.mutation;

import model.mutation.foodconsumption.strategy.Multiplier;

/**
 * Food Radar trait.
 */
public class FoodRadar extends AbstractTrait {

    private static final int MULTIPLIER = 2;

    /**
     * @param value
     * the trait value.
     */
    public FoodRadar(final int value) {
        super(value, new Multiplier(value, FoodRadar.MULTIPLIER), TraitType.FOODRADAR);
    }

}
