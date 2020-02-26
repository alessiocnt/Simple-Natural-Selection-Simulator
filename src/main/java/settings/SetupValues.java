package settings;

/**
 * Range of value available in the setup scene
 * and default value.
 *
 */
public enum SetupValues {

    /**
     * Dimension of entities.
     */
    DIMENSION(50, 200, 100),
    /**
     * Speed of entities.
     */
    SPEED(1, 5, 2),
    /**
     * Initial quantity of entities.
     */
    INITIALQUANTITY(50, 150, 100),
    /**
     * Quantity of food available every morning.
     */
    FOODQUANTITY(30, 300, 200),
    /**
     * Food variation every morning.
     */
    FOODVARIATION(-5, 5, 0);

    private final int start;
    private final int stop;
    private final int defaultValue;

    SetupValues(final int start, final int stop, final int defaultValue) {
        this.start = start;
        this.stop = stop;
        this.defaultValue = defaultValue;
    }

    /**
     * @return the start value.
     */
    public int getStart() {
        return this.start;
    }

    /**
     * @return the stop value.
     */
    public int getStop() {
        return this.stop;
    }

    /**
     * @return the default value.
     */
    public Integer getDefault() {
        return this.defaultValue;
    }
}
