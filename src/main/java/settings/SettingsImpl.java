package settings;

import java.awt.Toolkit;

import utilities.Pair;

/**
 * Implementation of settings.
 *
 */
public final class SettingsImpl implements Settings {

    private static final int PREFWIDTH = 1366;
    private static final int PREFHEIGHT = 768;
    private Pair<Integer, Integer> selectedRes;
    private final Pair<Integer, Integer> prefRes = new Pair<>(PREFWIDTH, PREFHEIGHT);
    private DayDuration dayDuration = DayDuration.getDefualt();

    /**
     * Constructor.
     */
    public SettingsImpl() {
        final int selectedWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(); 
        final int selectedHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.selectedRes = new Pair<>(selectedWidth, selectedHeight);
    }
    @Override
    public double getScaleFactor() {
        return Math.min(this.selectedRes.getX() / this.prefRes.getX(),
                this.selectedRes.getY() / this.prefRes.getY());
    }

    @Override
    public DayDuration getDayDuration() {
        return this.dayDuration;
    }

    @Override
    public void setDayDuration(final DayDuration duration) {
        this.dayDuration = duration;
    }

    @Override
    public int getWindowWidth() {
        synchronized (SettingsImpl.class) {
            return this.selectedRes.getX().intValue(); 
        }
    }

    @Override
    public int getWindowHeight() {
        synchronized (SettingsImpl.class) {
            return this.selectedRes.getY().intValue();
        }
    }

    @Override
    public int getPrefWindowWidth() {
        return this.prefRes.getX();
    }

    @Override
    public int getPrefWindowHeight() {
        return this.prefRes.getY();
    }

    @Override
    public String toString() {
        return "[Res: " + this.selectedRes + ", " + "Day Duration: " + this.dayDuration + "]";
    }
    @Override
    public void setWidth(final int width) {
        synchronized (SettingsImpl.class) {
            this.selectedRes = new Pair<Integer, Integer>(width, this.selectedRes.getY());
        }
    }
    @Override
    public void setHeight(final int height) {
        synchronized (SettingsImpl.class) {
            this.selectedRes = new Pair<Integer, Integer>(this.selectedRes.getX(), height);
        }
    }
}
