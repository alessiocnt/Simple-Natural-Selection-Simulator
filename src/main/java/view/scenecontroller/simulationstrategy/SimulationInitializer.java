package view.scenecontroller.simulationstrategy;

/**
 * Interface that permit to inizialize and update graphics in the simulation.
 */
public interface SimulationInitializer {

    /**
     * @param rootWidth
     * width of the root.
     * @param rootHeight
     * height of the root.
     */
    void initSimulationController(double rootWidth, double rootHeight);

    /**
     * Adjust canvas dimension.
     * @param rootWidth 
     * @param rootHeight 
     */
    void adjustCanvas(double rootWidth, double rootHeight);
}
