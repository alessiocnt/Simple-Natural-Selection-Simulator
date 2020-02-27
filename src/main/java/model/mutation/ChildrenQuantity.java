package model.mutation;

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
        super(value, (organism) -> {
            return value * ChildrenQuantity.MULTIPLIER;
        });
    }

    @Override
    public final MutationRarity getRarity() {
        return TraitType.CHILDRENQUANTITY.getRarity();
    }

}
