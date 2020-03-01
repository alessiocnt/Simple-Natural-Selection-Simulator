package view.scenecontroller;

import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Map.Entry;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;
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
        System.out.println(this.getView());
        this.getView().setSimulationController(this);
        this.logics = new SimulationViewLogicsImpl(this.canvas.getGraphicsContext2D(), 100, 100);
        this.getView().getController().startSimulation();
                /*(int) this.getView().getController().getEnvironmentDimension().getX(),
                (int) this.getView().getController().getEnvironmentDimension().getY());*/
    }

    @FXML
    private void initialize() {
        System.out.println("Wassup " + this.canvas.getHeight());
    }

    /**
     * Updates the canvas with Environment parameters.
     * 
     * @param foods
     *                      food that will be displayed
     * @param organisms
     *                      organisms that will be displayed
     */
    public void render(final Set<Entry<Position, Food>> foods, final Set<Entry<Position, Organism>> organisms) {
        this.canvas.setWidth(this.getView().getController().getSettings().getPrefWindowWidth());
        this.canvas.setHeight(this.getView().getController().getSettings().getPrefWindowHeight()
               - this.top.getHeight() - this.bottom.getHeight());
        this.logics.setCanvasDimension(this.canvas.getWidth(), this.canvas.getHeight());
        this.logics.setEntities(foods, organisms);
        this.logics.update();
    }
}
