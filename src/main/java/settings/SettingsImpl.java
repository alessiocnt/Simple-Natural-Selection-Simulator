package settings;

import java.awt.Toolkit;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;

/**
 * Implementation of settings.
 *
 */
public final class SettingsImpl implements Settings {

    private static final int PREFWIDTH = 800;
    private static final int PREFHEIGHT = 600;
    private final MutablePair<Integer, Integer> selectedRes;
    private final ImmutablePair<Integer, Integer> prefRes = ImmutablePair.of(PREFWIDTH, PREFHEIGHT);
    private DayDuration dayDuration = DayDuration.getDefualt();

    /**
     * Constructor.
     */
    public SettingsImpl() {
        final int selectedWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(); 
        final int selectedHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.selectedRes = MutablePair.of(selectedWidth, selectedHeight);
    }

    @Override
    public double getScaleFactor() {
        return Math.min(this.selectedRes.getKey() / this.prefRes.getKey(),
                this.selectedRes.getValue() / this.prefRes.getValue());
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
        return this.selectedRes.getKey().intValue(); 
    }

    @Override
    public int getWindowHeight() {
        return this.selectedRes.getValue().intValue();
    }

    @Override
    public int getPrefWindowWidth() {
        return this.prefRes.getKey();
    }

    @Override
    public int getPrefWindowHeight() {
        return this.prefRes.getValue();
    }

    @Override
    public String toString() {
        return "[Res: " + this.selectedRes + ", " + "Day Duration: " + this.dayDuration + "]";
    }

    @Override
    public void setWidth(final int width) {
        this.selectedRes.setLeft(width);
    }

    @Override
    public void setHeight(final int height) {
        this.selectedRes.setRight(height);
    }
}
