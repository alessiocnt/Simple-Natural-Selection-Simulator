package view.scenecontroller;

import java.util.Set;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;
import utilities.Pair;
import view.scenecontroller.simulationstrategy.SimulationViewLogics;
import view.scenecontroller.simulationstrategy.SimulationViewLogicsImpl;

/**
 * Simulation controller.
 *
 */
public class SimulationController extends AbstractSceneController {

    @FXML
    private Canvas canvas;

    @FXML
    private Button startStopBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Label aliveLbl;

    @FXML
    private HBox top;

    @FXML
    private HBox bottom;

    private SimulationViewLogics logics;

    @FXML
    private void startStop() {
        this.getView().getController().startStopSimulation();
    }

    @FXML
    private void backClick() {
        if (this.getView().getController().isSimulationRunning()) {
            this.getView().getController().startStopSimulation();
        }
        this.getSceneFactory().openSetup();
    }

    @FXML
    private void settingsClick() {
        if (this.getView().getController().isSimulationRunning()) {
            this.getView().getController().startStopSimulation();
        }
        this.getSceneFactory().openSettings();
    }

    /**
     * Initializes the simulation controller.
     */
    public void initSimulationController() {
        final int width = 200;
        final int height = 100;
        this.getView().setSimulationController(this);
        this.logics = new SimulationViewLogicsImpl(this.canvas.getGraphicsContext2D(), width, height);
        //Initialize the canvas dimension.
        this.canvas.setWidth(this.getView().getController().getSettings().getWindowWidth());
        this.canvas.setHeight(this.getView().getController().getSettings().getWindowHeight()
                  - this.top.getHeight() - this.bottom.getHeight());
        this.logics.setCanvasDimension(this.canvas.getWidth(), this.canvas.getHeight());
        /*(int) this.getView().getController().getEnvironmentDimension().getX(),
        (int) this.getView().getController().getEnvironmentDimension().getY());*/
        this.getView().getController().startSimulation();
    }
    /**
     * Updates the canvas with Environment parameters.
     * 
     * @param foods
     *                      food that will be displayed
     * @param organisms
     *                      organisms that will be displayed
     */
    public void render(final Set<Pair<Position, Food>> foods, final Set<Pair<Position, Organism>> organisms) {
        Platform.runLater(() -> {
            this.logics.setEntities(foods, organisms);
            this.logics.update();
            this.aliveLbl.setText(String.valueOf(this.logics.getAlive()));
        });
    }

    /**
     * Adjust canvas dimension.
     */
    public void adjustCanvas() {
         this.canvas.setWidth(this.getView().getController().getSettings().getWindowWidth());
         this.canvas.setHeight(this.getView().getController().getSettings().getWindowHeight()
                   - this.top.getHeight() - this.bottom.getHeight());
         this.logics.setCanvasDimension(this.canvas.getWidth(), this.canvas.getHeight());
         this.logics.update();
    }
}
