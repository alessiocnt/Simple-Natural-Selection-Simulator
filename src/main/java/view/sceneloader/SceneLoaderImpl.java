package view.sceneloader;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import view.View;
import view.scenecontroller.SceneController;
import view.scenecontroller.SimulationController;
import view.utilities.SceneType;

/**
 * Implementation of Scene Loader that is responsible of loading the right scene
 * in the current stage.
 */
public class SceneLoaderImpl implements SceneLoader {

    private FXMLLoader loader;
    private final View view;
    private final Map<SceneType, Optional<Scene>> sceneCache = new EnumMap<>(SceneType.class);

    /**
     * @param view
     * Current view
     */
    public SceneLoaderImpl(final View view) {
        this.view = view;
    }

    @Override
    public final void setScene(final SceneType sceneType, final Stage stage) {
        this.loader = new FXMLLoader();
        this.loader.setLocation(ClassLoader.getSystemResource(sceneType.getFxmlPath()));
        try {
            Region root = this.loader.load();
            root.setPrefSize(this.view.getController().getSettings().getWindowWidth(),
                    this.view.getController().getSettings().getWindowHeight());

            root.widthProperty().addListener((obs, oldVal, newVal) -> {
                this.view.getController().setWidth(newVal.intValue());
            });

            root.heightProperty().addListener((obs, oldVal, newVal) -> {
                this.view.getController().setHeight(newVal.intValue());
            });

            final Scene scene;
            if (this.sceneCache.containsKey(sceneType)) {
                scene = this.sceneCache.get(sceneType).get();
            } else {
                scene = new Scene(root);
                scene.getStylesheets().add(ClassLoader.getSystemResource(sceneType.getCssPath()).toExternalForm());
                this.sceneCache.put(sceneType, Optional.of(scene));
            }

            stage.setScene(scene);
            stage.setTitle(sceneType.getTitle());
            stage.setMinWidth(this.view.getController().getSettings().getPrefWindowWidth());
            stage.setMinHeight(this.view.getController().getSettings().getPrefWindowHeight());
            stage.setResizable(true);

            if (!stage.isShowing()) {
                stage.show();
            }

            SceneController controller = (SceneController) this.loader.getController();
            controller.setSceneFactory(this.view.getSceneFactory());
            controller.setView(this.view);

            switch (sceneType) {
                case SIMULATION:
                    ((SimulationController) controller).initSimulationController();
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
