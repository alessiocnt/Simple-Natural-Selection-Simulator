package model.mutation;

import model.mutation.foodconsumption.strategy.Multiplier;
import model.mutation.utilities.CheckUtil;

/**
 * Children Quantity Trait.
 *
 */
public class ChildrenQuantity extends AbstractTrait {

    private static final int MULTIPLIER = 10;

    /**
     * @param value
     * children quantity.
     */
    public ChildrenQuantity(final int value) {
        super(value, new Multiplier(value, ChildrenQuantity.MULTIPLIER));
        CheckUtil.check(() -> value > 0, new IllegalArgumentException());
    }

    @Override
    public final MutationRarity getRarity() {
        return TraitType.CHILDRENQUANTITY.getRarity();
    }

}
