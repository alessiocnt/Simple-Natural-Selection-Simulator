package view.observers;

import view.View;

/**
 * Interface for observer in the Settings scene.
 */
public interface SettingsObserver {
    /**
     * @param view
     * The current View reference.
     */
    void update(View view);
}
