package model.mutation;

/**
 * Types of trait.
 */
public enum TraitType {
    /**
     * Speed.
     */
    SPEED(AffectMovement.YES, MutationRarity.RARE),

    /**
     * Dimension.
     */

    DIMENSION(AffectMovement.YES, MutationRarity.RARE),
    /**
     * Food Radar.
     */
    //FOODRADAR(AffectMovement.YES, MutationRarity.RARE),

    /**
     * Children quantity.
     */
    CHILDRENQUANTITY(AffectMovement.NO, MutationRarity.RARE);

    private AffectMovement affectMovement;
    private MutationRarity rarity;

    TraitType(final AffectMovement affectMovement, final MutationRarity rarity) {
        this.affectMovement = affectMovement;
        this.rarity = rarity;
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

}
