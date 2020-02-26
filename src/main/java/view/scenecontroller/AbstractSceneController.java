package view.scenecontroller;

import view.scenefactory.SceneFactory;

/**
 * Abstract Scene Controller that implement getter and setter for
 * sceneFactory.
 *
 */
public class AbstractSceneController implements SceneController{

    private SceneFactory sceneFactory;

    @Override
    public final void setSceneFactory(final SceneFactory sceneFactory) {
        this.sceneFactory = sceneFactory;
    }

    @Override
    public final SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }

}
