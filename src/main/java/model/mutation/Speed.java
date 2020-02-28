package model.mutation;

import model.mutation.foodconsumption.strategy.Multiplier;
import model.mutation.utilities.CheckUtil;

/**
 * Speed trait.
 */
public class Speed extends AbstractTrait {

    private static final int MULTIPLIER = 5;

    /**
     * @param value
     * the Speed value
     */
    public Speed(final int value) {
        super(value, new Multiplier(value, Speed.MULTIPLIER));
        CheckUtil.check(() -> value > 0, new IllegalArgumentException());
    }

    @Override
    public final MutationRarity getRarity() {
        return TraitType.SPEED.getRarity();
    }
}
