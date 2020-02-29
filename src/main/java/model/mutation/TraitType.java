package model.mutation;

import settings.SetupValues;

/**
 * Types of trait.
 */
public enum TraitType {
    /**
     * Speed.
     */
    SPEED(AffectMovement.YES, MutationRarity.COMMON, SetupValues.SPEED),

    /**
     * Dimension.
     */

    DIMENSION(AffectMovement.YES, MutationRarity.NORMAL, SetupValues.DIMENSION),
    /**
     * Food Radar.
     */
    //FOODRADAR(AffectMovement.YES, MutationRarity.RARE),

    /**
     * Children quantity.
     */
    CHILDRENQUANTITY(AffectMovement.NO, MutationRarity.RARE, SetupValues.CHILDRENQUANTITY);

    private AffectMovement affectMovement;
    private MutationRarity rarity;
    private SetupValues values;

    TraitType(final AffectMovement affectMovement, final MutationRarity rarity, final SetupValues values) {
        this.affectMovement = affectMovement;
        this.rarity = rarity;
        this.values = values;
    }

    /**
     * @return true if affect movement.
     */
    public boolean affectMovement() {
        return this.affectMovement.equals(AffectMovement.YES);
    }

    /**
     * @return the rarity.
     */
    public MutationRarity getRarity() {
        return this.rarity;
    }

    /**
     * @return the range of value that trait can assume.
     */
    public SetupValues getValues() {
        return this.values;
    }

}
