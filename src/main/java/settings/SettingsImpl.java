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
    private final Pair<Double, Double> selectedRes;
    private final Pair<Integer, Integer> prefRes = new Pair<>(PREFWIDTH, PREFHEIGHT);
    private DayDuration dayDuration = DayDuration.NORMAL;

    /**
     * Constructor.
     */
    public SettingsImpl() {
        final double selectedWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth(); 
        final double selectedHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.selectedRes = new Pair<>(selectedWidth, selectedHeight);
    }
    @Override
    public double getScaleFactor() {
        return Math.min(this.selectedRes.getX() / this.prefRes.getX(),
                this.selectedRes.getY() / this.selectedRes.getY());
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
        return this.selectedRes.getX().intValue();
    }
    @Override
    public int getWindowHeight() {
        return this.selectedRes.getY().intValue();
    }
    @Override
    public int getPrefWindowWidth() {
        return this.prefRes.getX();
    }
    @Override
    public int getPrefWindowHeight() {
        return this.prefRes.getY();
    }

}
