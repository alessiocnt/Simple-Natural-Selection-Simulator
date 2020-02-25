package settings;

/**
 * Interface for Settings holder, which only permit 
 * read operations. 
 * It is useful when the view needs Settings
 *
 */
public interface SettingsHolder {
    /**
     * @return the scale factor to apply.
     */
    double getScaleFactor();
    /**
     * @return the duration of the day.
     */
    DayDuration getDayDuration();
}
