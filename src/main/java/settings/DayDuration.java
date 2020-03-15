package settings;

/**
 * Enumeration that describe the duration of the day.
 *
 */
public enum DayDuration {
    /**
     * Fast, 2 seconds.
     */
    FAST(2, "Fast"),

    /**
     * Normal, 6 seconds.
     */
    NORMAL(6, "Normal"),

    /**
     * Slow, 15 seconds.
     */
    SLOW(10, "Slow");


    private final int duration;
    private final String name;

    DayDuration(final int duration, final String name) {
        this.duration = duration;
        this.name = name;
    }

    /**
     * @return the duration of the selected enum
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * @return the String rapresentation of the enum.
     */
    public String toString() {
        return this.name;
    }

    /**
     * @return the default value.
     */
    public static DayDuration getDefualt() {
        return DayDuration.NORMAL;
    }
}
