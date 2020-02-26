package view;

import controller.Controller;
import javafx.stage.Stage;
import view.scenefactory.SceneFactory;

/**
 * View implementation.
 *
 */
public class ViewImpl implements View {

    private final Stage stage;
    private Controller controller;

    /**
     * @param stage
     * the stage where tha app run.
     */
    public ViewImpl(final Stage stage) {
        this.stage = stage;
    }

    @Override 
    public final void launch(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public final Controller getController() {
        return this.controller;
    }

    @Override
    public final SceneFactory getSceneFactory() {
        // TODO Auto-generated method stub
        return null;
    }

}
