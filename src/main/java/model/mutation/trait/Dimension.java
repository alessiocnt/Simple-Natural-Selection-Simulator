package model.mutation.trait;

import model.mutation.TraitType;
import model.mutation.foodconsumption.strategy.Multiplier;

/**
 * Dimension trait.
 */
public class Dimension extends AbstractTrait {

    private static final double MULTIPLIER = 0.1;

    /**
     * @param value
     * the dimension value;
     */
    public Dimension(final int value) {
        super(value, new Multiplier(value, Dimension.MULTIPLIER), TraitType.DIMENSION);
    }

}
