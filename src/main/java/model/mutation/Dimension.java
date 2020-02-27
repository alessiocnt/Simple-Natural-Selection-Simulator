package model.mutation;

/**
 * Dimension trait.
 */
public class Dimension extends AbstractTrait {

    private static final double MULTIPLIER = 0.1;

    Dimension(final int value) {
        super(value, (organism) -> {
            return ((Double) (value * Dimension.MULTIPLIER)).intValue();
        });
    }

    @Override
    public final MutationRarity getRarity() {
        return TraitType.DIMENSION.getRarity();
    }

}
