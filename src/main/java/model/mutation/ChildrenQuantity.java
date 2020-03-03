package model.mutation;

import model.mutation.foodconsumption.strategy.Multiplier;

/**
 * Children Quantity Trait.
 */
public class ChildrenQuantity extends AbstractTrait {

    private static final int MULTIPLIER = 20;

    /**
     * @param value
     * children quantity.
     */
    public ChildrenQuantity(final int value) {
        super(value, new Multiplier(value, ChildrenQuantity.MULTIPLIER), TraitType.CHILDRENQUANTITY);
    }

    @Override
    public final MutationRarity getRarity() {
        return TraitType.CHILDRENQUANTITY.getRarity();
    }

}
