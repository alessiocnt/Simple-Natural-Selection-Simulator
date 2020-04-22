package view.scenefactory;

import javafx.stage.Stage;
import view.View;
import view.sceneloader.SceneLoader;
import view.sceneloader.SceneLoaderImpl;
import view.utilities.SceneType;

/**
 * Scene Factory implementation.
 */
public class SceneFactoryImpl implements SceneFactory {

    private final Stage stage;
    private final SceneLoader sceneLoader;

    /**
     * @param view
     * current view
     * @param stage
     * current stage
     */
    public SceneFactoryImpl(final Stage stage, final View view) {
        this.stage = stage;
        this.sceneLoader = new SceneLoaderImpl(view);
    }

    @Override
    public final void openSetup() {
        this.openScene(SceneType.SETUP);
    }

    @Override
    public final void openSimulation() {
        this.openScene(SceneType.SIMULATION);
    }

    @Override
    public final void openSettings() {
        this.openScene(SceneType.SETTINGS);
    }

    private void openScene(final SceneType sceneType) {
        this.sceneLoader.setScene(sceneType, this.stage);
    }

}
