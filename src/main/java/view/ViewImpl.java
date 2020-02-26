package view;

import controller.Controller;
import javafx.stage.Stage;
import view.scenefactory.SceneFactory;
import view.scenefactory.SceneFactoryImpl;

/**
 * View implementation.
 *
 */
public class ViewImpl implements View {

    private final Stage stage;
    private Controller controller;
    private final SceneFactory sceneFactory;

    /**
     * @param stage
     * the stage where tha app run.
     */
    public ViewImpl(final Stage stage) {
        this.stage = stage;
        this.sceneFactory = new SceneFactoryImpl(this.stage, this);
    }

    @Override 
    public final void launch(final Controller controller) {
        this.controller = controller;
        this.sceneFactory.openSetup();
    }

    @Override
    public final Controller getController() {
        return this.controller;
    }

    @Override
    public final SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }

}
