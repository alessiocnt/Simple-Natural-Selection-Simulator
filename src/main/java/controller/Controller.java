package controller;

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
     * @return true if the simulation start correctly.
     */
    boolean initSimulation();

    /**
     * Method to start simulation.
     */
    void startSimulation();

    /**
     * Method to stop simulation.
     */
    void stopSimulation();

    /**
     * @param duration
     * the duration of the day to be set in Settings.
     */
    void setDayDuration(DayDuration duration);

    /**
     * @return the settings in read-only.
     */
    SettingsHolder getSettings();
}
