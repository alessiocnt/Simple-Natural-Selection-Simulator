package view.observers;

import view.entities.EnvironmentHolder;

/**
 * Interface of observers for setting up the environment.
 *
 */
public interface SetupObserver {
    /**
     * Set the right parameter in the holder.
     * @param holder
     * the holder that need to be set
     */
    void update(EnvironmentHolder holder);
}
