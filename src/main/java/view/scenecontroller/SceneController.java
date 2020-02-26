package view.scenecontroller;

import view.View;
import view.scenefactory.SceneFactory;

/**
 * Base interface for scene controller.
 *
 */
public interface SceneController {
    /**
     * @param sceneFactory
     * sceneFactory to be used.
     */
    void setSceneFactory(SceneFactory sceneFactory);

    /**
     * @return the current sceneFactory.
     */
    SceneFactory getSceneFactory();

    /**
     * @param view
     * set current view.
     * For example to get the controller
     */
    void setView(View view);

    /**
     * @return the current view;
     */
    View getView();
}
