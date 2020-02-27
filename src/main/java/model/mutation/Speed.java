package model.mutation;

/**
 * Speed trait.
 */
public class Speed extends AbstractTrait {

    private static final int MULTIPLIER = 5;

    Speed(final int value) {
        super(value, (organism) -> {
            return value * Speed.MULTIPLIER;
        });
    }

    @Override
    public final MutationRarity getRarity() {
        return TraitType.SPEED.getRarity();
    }
}
