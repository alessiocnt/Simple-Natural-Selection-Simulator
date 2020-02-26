package view;

import controller.Controller;
import view.scenefactory.SceneFactory;

/**
 * View interface.
 *
 */
public interface View {
    /**
     * @param controller
     * controller of the simulator.
     */
    void launch(Controller controller);

    /**
     * @return the controller.
     */
    Controller getController();

    /**
     * @return the current sceneFactory.
     */
    SceneFactory getSceneFactory();

}
