package view.sceneloader;

import javafx.stage.Stage;
import view.utilities.SceneType;

/**
 * Base interface for SceneLoader.
 */
public interface SceneLoader {
    /**
     * @param sceneType
     * the scene to be loaded.
     * @param stage
     * current stage where load the scene.
     */
    void setScene(SceneType sceneType, Stage stage);
}
