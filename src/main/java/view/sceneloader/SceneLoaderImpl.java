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
        try {
            final Region root;
            final Scene scene;

            //If the scene is already in cache, set the cached scene.
            if (this.sceneCache.containsKey(sceneType)) {
                scene = this.sceneCache.get(sceneType).get();
                this.loader = (FXMLLoader) scene.getUserData();
                root = (Region) scene.getRoot();
            } else {
                //If the scene isn't in the cache, then create a new one, with the current root.
                this.loader = new FXMLLoader();
                this.loader.setLocation(ClassLoader.getSystemResource(sceneType.getFxmlPath()));
                root = this.createRoot();
                scene = new Scene(root, (double) this.view.getController().getSettings().getPrefWindowWidth(),
                        (double) this.view.getController().getSettings().getPrefWindowHeight());
                scene.setUserData(this.loader);
                scene.getStylesheets().add(ClassLoader.getSystemResource(sceneType.getCssPath()).toExternalForm());
                this.sceneCache.put(sceneType, Optional.of(scene));
            }

            //Add listener to set the current size in settings.
            stage.widthProperty().addListener((obs, oldVal, newVal) -> {
                this.view.getController().setWidth(newVal.intValue());
            });
            stage.heightProperty().addListener((obs, oldVal, newVal) -> {
                this.view.getController().setHeight(newVal.intValue());
            });

            stage.setTitle(sceneType.getTitle());
            stage.setResizable(true);
            stage.setScene(scene);

            stage.setHeight(this.view.getController().getSettings().getWindowHeight());
            stage.setWidth(this.view.getController().getSettings().getWindowWidth());

            stage.close();
            stage.show();

            final SceneController controller = (SceneController) this.loader.getController();
            this.initializeScene(controller, sceneType, scene, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Initialize scene by call methods, for example in startSimulation we need to call some
     * methods in order to init the environment view.
     */
    private void initializeScene(final SceneController controller, final SceneType sceneType, final Scene scene, final Region root) {
        controller.setSceneFactory(this.view.getSceneFactory());
        controller.setView(this.view);

        switch (sceneType) {
            case SIMULATION:
                final SimulationController simulationController = (SimulationController) controller;
                simulationController.initSimulationController(this.view.getController().getSettings().getWindowWidth() - scene.getX(),
                        this.view.getController().getSettings().getWindowHeight() - scene.getY());
                //Add another listener, so when dimension change the canvas will resize properly.
                root.widthProperty().addListener((obs, oldVal, newVal) -> {
                    simulationController.adjustCanvas(newVal.intValue(), root.getHeight());
                });
                root.heightProperty().addListener((obs, oldVal, newVal) -> {
                    simulationController.adjustCanvas(root.getWidth(), newVal.intValue());
                });
                break;
            default:
                break;
        }
    }

    /*
     * Create root with resolution listener.
     */
    private Region createRoot() throws IOException {
        final Region root = this.loader.load();
        return root;
    }
}

