package view.observers;

import view.entities.EnvironmentHolder;

/**
 * Interface that hold all the observers for setting up the environment.
 *
 */
public interface SetupObserver {
    /**
     * Set the right parameter in the holder.
     * @param holder
     * the holder that need to be set
     */
    void update(EnvironmentHolder holder);

    /**
     * @return true if the input is valid, false othrewise.
     */
    boolean validate();
}
