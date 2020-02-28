package model.mutation;

import model.mutation.foodconsumption.strategy.Multiplier;
import model.mutation.utilities.CheckUtil;

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
        super(value, new Multiplier(value, Dimension.MULTIPLIER));
        CheckUtil.check(() -> value > 0, new IllegalArgumentException());
    }

    @Override
    public final MutationRarity getRarity() {
        return TraitType.DIMENSION.getRarity();
    }

}
