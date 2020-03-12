package settings;

/**
 * Range of value of traits and properties.
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
    FOODQUANTITY(200, 2000, 1750),
    /**
     * Food variation every morning.
     */
    FOODVARIATION(-5, 5, 0),
    /**
     * Children quantity.
     */
    CHILDRENQUANTITY(1, 3, 1),
    /**
     * Food radar values.
     */
    FOODRADAR(0, 2, 1),
    /**
     * Temperature of environment.
     */
    TEMPERATURE(20, 30, 25);

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
