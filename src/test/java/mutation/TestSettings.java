package mutation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Toolkit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import settings.DayDuration;
import settings.Settings;
import settings.SettingsImpl;

/**
 * Test of settins.
 */
public class TestSettings {
    private static final DayDuration DURATION = DayDuration.SLOW; 
    private Settings settings;

    /**
     * Initialise settings.
     */
    @BeforeEach
    public void initialise() {
        this.settings = new SettingsImpl();
        this.settings.setDayDuration(DURATION);
    }

    /**
     * Test the resolution.
     * Resolution must be the resolution of the screen.
    */
    @Test
    public void testResolution() {
        assertEquals((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                this.settings.getWindowWidth());
        assertEquals((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(),
                this.settings.getWindowHeight());
    }
}
