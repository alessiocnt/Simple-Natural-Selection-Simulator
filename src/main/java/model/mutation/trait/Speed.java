package model.mutation.trait;

import model.mutation.TraitType;
import model.mutation.foodconsumption.strategy.Multiplier;

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
        super(value, new Multiplier(value, Speed.MULTIPLIER), TraitType.SPEED);
    }

}
