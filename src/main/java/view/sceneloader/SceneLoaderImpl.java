package view.sceneloader;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
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
        try {
            final Region root;
            final Scene scene;

            //If the scene is already in cache, set the cached scene.
            if (this.sceneCache.containsKey(sceneType)) {
                scene = this.sceneCache.get(sceneType).get();
                this.loader = (FXMLLoader) scene.getUserData();
                root = (Region) scene.getRoot();
                //Set the actual resolution to the root.
                root.setPrefSize(this.view.getController().getSettings().getWindowWidth(),
                        this.view.getController().getSettings().getWindowHeight());
            } else {
                //If the scene isn't in the cache, then create a new one, with the current root.
                this.loader = new FXMLLoader();
                this.loader.setLocation(ClassLoader.getSystemResource(sceneType.getFxmlPath()));
                root = this.createRoot();
                scene = new Scene(root);
                scene.setUserData(this.loader);
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

            final SceneController controller = (SceneController) this.loader.getController();
            controller.setSceneFactory(this.view.getSceneFactory());
            controller.setView(this.view);

            switch (sceneType) {
                case SIMULATION:
                    final SimulationController simulationController = ((SimulationController) controller);
                    simulationController.initSimulationController();
                    //Add another listener, so when dimension change the canvas will resize properly.
                    root.widthProperty().addListener((obs, oldVal, newVal) -> {
                        simulationController.adjustCanvas();
                    });
                    root.heightProperty().addListener((obs, oldVal, newVal) -> {
                        simulationController.adjustCanvas();
                    });
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Region createRoot() throws IOException {
        final Region root = this.loader.load();
        //Add listener to set the current size in settings.
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.view.getController().setWidth(newVal.intValue());
            this.scaleViews(root);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            this.view.getController().setHeight(newVal.intValue());
            this.scaleViews(root);
        });
        root.setPrefSize(this.view.getController().getSettings().getWindowWidth(),
                this.view.getController().getSettings().getWindowHeight());
        return root;
    }
    private void scaleViews(final Parent root) {
        /*root.getChildrenUnmodifiable().forEach((x) -> {
            if (x instanceof Parent) {
                root.getChildrenUnmodifiable().stream().filter((y) -> ((Parent) y).getChildrenUnmodifiable().size() != 0)
                    .forEach((y) -> {
                        this.scaleViews((Parent) y);
                    });
            }
            if (x instanceof Label || x instanceof Button || x instanceof ComboBox) {
                final double scaleFactor = this.view.getController().getSettings().getScaleFactor();
                ((Control) x).setMinSize(((Control) x).getWidth() * scaleFactor, ((Control) x).getHeight() * scaleFactor);
                ((Control) x).setStyle("-fx-font-size: 100%;");
            }
        });*/
    }
}

