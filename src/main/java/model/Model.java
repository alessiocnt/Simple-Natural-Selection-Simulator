package model;

import model.environment.daycicle.DayCicle;

/**
 * Model interface.
 *
 */
public interface Model {

    /**
     * Updates all the objects in the simulation.
     * @param dayCicle
     *      the controller of day/night in the simulation
     */
    void update(DayCicle dayCicle);

    /**
     * @return true if the simulation is over
     */
    boolean isSimulationOver();
}
