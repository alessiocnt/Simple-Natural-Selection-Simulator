package settings;

/**
 * Interface for Settings holder, which only permit 
 * read operations. 
 * It is useful when the view needs Settings
 *
 */
public interface SettingsHolder {
    /**
     * @return the duration of the day.
     */
    DayDuration getDayDuration();

    /**
     * @return the window width.
     */
    int getWindowWidth();

    /**
     * @return the window height.
     */
    int getWindowHeight();

    /**
     * @return the preferite window width.
     */
    int getPrefWindowWidth();

    /**
     * @return the preferite window height.
     */
    int getPrefWindowHeight();
}
