package view.scenecontroller.simulationstrategy;

/**
 * Interface that permit to inizialize and update graphics in the simulation.
 */
public interface SimulationInitializer {

    /**
     * Initialize simulation.
     */
    void initSimulationController();

    /**
     * Adjust canvas dimension.
     */
    void adjustCanvas();
}
