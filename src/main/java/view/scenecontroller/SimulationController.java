package view.scenecontroller;

import java.util.Set;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;
import utilities.Pair;
import view.scenecontroller.simulationstrategy.SimulationViewLogics;
import view.scenecontroller.simulationstrategy.SimulationViewLogicsImpl;
import view.utilities.MyAlert;

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

    @FXML
    private VBox lateralPane;

    @FXML
    private ScrollPane scrollPane;

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

        this.lateralPane.getChildren().clear();
        this.createGraphs();

        //Initialize the canvas dimension.
        this.adjustCanvas();
        /*(int) this.getView().getController().getEnvironmentDimension().getX(),
        (int) this.getView().getController().getEnvironmentDimension().getY());*/
        this.getView().getController().startSimulation();

    }

    /*
     * ----SOLO UNA PROVA----
     * Prima bisogna mettere apposto il render e il fatto della dipendenza tra View e Model
     * poi probabilmente creo una classe a parte per i grafici, da cui gestisco creazione ed update.
     */
    private LineChart<Number, Number> createGraph(final String title) {
        final NumberAxis xAxis = new NumberAxis(); // we are gonna plot against time
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time/s");
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("Value");
        yAxis.setAnimated(false); // axis animations are removed
        //creating the line chart with two axis created above
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(title);
        //defining a series to display data
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        // add series to chart
        lineChart.getData().add(series);
        lineChart.setLegendVisible(false);
        return lineChart;
    }

    private void createGraphs() {
        final LineChart<Number, Number> lineChart = this.createGraph("Prova");
        final LineChart<Number, Number> lineChart1 = this.createGraph("Prova1");
        this.lateralPane.getChildren().add(lineChart);
        this.lateralPane.getChildren().add(lineChart1);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.autosize();
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
         this.canvas.setWidth(this.getView().getController().getSettings().getWindowWidth() - this.scrollPane.getWidth());
         this.canvas.setHeight(this.getView().getController().getSettings().getWindowHeight()
                   - this.top.getHeight() - this.bottom.getHeight());
         this.logics.setCanvasDimension(this.canvas.getWidth(), this.canvas.getHeight());
         this.logics.update();

    }

    /**
     * Tells SimulationController that the simulation is over.
     */
    public void simulationOver() {
        Platform.runLater(() -> {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("INFO");
            a.setContentText("The simulation is over, everyone is dead.");
            a.show();
        });
    }
}
