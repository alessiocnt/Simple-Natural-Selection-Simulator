package view.observers;

import view.View;

/**
 * Interface for observer in the Settings scene.
 */
public interface SettingsObserver {
    /**
     * @param view
     * The currente View reference.
     */
    void update(View view);
}
