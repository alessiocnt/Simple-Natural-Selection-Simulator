package model.mutation;

/**
 * Enum that describe the rarity.
 *
 */
public enum MutationRarity {
    /**
     * No mutation for this trait.
     */
    NOMUTATION(0),
    /**
     * Very rare.
     */
    VERYRARE(0.10),
    /**
     * Rare.
     */
    RARE(0.20),
    /**
     * Normal.
     */
    NORMAL(0.50),
    /**
     * Common.
     */
    COMMON(0.70),
    /**
     * Very Common. 
     */
    VERYCOMMON(0.90);

    //The percentace of rarity.
    private final double perc;

    /**
     * @param perc
     * The percentage of mutation;
     */
    MutationRarity(final double perc) {
        this.perc = perc;
    }

    /**
     * @return the percentage.
     */
    public double getPercentage() {
        return this.perc;
    }
}
