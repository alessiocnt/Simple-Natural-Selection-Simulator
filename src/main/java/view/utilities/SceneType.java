package view.utilities;

/**
 * Enumeration that contains all the informations about scenes.
 *
 */
public enum SceneType {

    /**
     * Setup scene.
     */
    SETUP("layouts/environmentSetup.fxml", "css/environmentSetup.css", "Setup"),
    /**
     * Simulation scene. 
     */
    SIMULATION("layouts/simulation.fxml", "css/simulation.css", "Simulation"),
    /**
     * Settings scene.
     */
    SETTINGS("layouts/settings.fxml", "css/settings.css", "Settings");

    private final String fxmlPath;
    private final String cssPath;
    private final String title;

    /**
     * @param fxmlPath
     * fxml path of the scene
     * @param cssPath
     * css path of the scene
     * @param title
     * title of the scene
     */
    SceneType(final String fxmlPath, final String cssPath, final String title) {
        this.fxmlPath = fxmlPath;
        this.cssPath = cssPath;
        this.title = title;
    }

    /**
     * @return the fxml path of the scene.
     */
    public String getFxmlPath() {
        return this.fxmlPath;
    }

    /**
     * @return the css path of the scene.
     */
    public String getCssPath() {
        return this.cssPath;
    }

    /**
     * @return the fxml path of the scene.
     */
    public String getTitle() {
        return this.title;
    }
}
