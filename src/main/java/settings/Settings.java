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
}
