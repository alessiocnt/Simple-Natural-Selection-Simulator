package settings;

/**
 * Settings interface that extends SettingsHolder
 * This interface allow write.
 *
 */
public interface Settings extends SettingsHolder {
    /**
     * @param duration
     * the day duration selected.
     */
    void setDayDuration(DayDuration duration);
    /**
     * @param width
     * Width to set.
     */
    void setWidth(int width);
    /**
     * @param height
     * height to set.
     */
    void setHeight(int height);
}
