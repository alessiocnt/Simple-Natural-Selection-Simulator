package view.scenecontroller;

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
}
