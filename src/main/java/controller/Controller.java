package controller;

import model.environment.position.Position;
import settings.DayDuration;
import settings.SettingsHolder;
import view.entities.EnvironmentHolder;

/**
 * Controller Interface.
 *
 */
public interface Controller {
    /**
     * @param holder
     * the environment holder created by the view.
     */
    void setEnvironmentInitialValues(EnvironmentHolder holder);

    /**
     * @param duration
     * the duration of the day to be set in Settings.
     */
    void setDayDuration(DayDuration duration);

    /**
     * @return the settings in read-only.
     */
    SettingsHolder getSettingsHolder();

    /**
     * @return the environment dimension
     */
    Position getEnvironmentDimension();

    /**
     * Method that starts the simulation if it's paused, or pauses it if it's running.
     */
    void startStopSimulation();

    /**
     * @param width
     * Window width to set.
     */
    void setWidth(int width);
    /**
     * @param height
     * Window height to set.
     */
    void setHeight(int height);

    /**
     * Starts the simulation.
     */
    void startSimulation();

    /**
     * @return
     *      true if the simulation is currently running
     */
    boolean isSimulationRunning();

}
