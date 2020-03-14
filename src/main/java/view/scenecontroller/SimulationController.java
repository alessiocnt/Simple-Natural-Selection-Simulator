package view.scenecontroller;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
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
import model.mutation.MutationRarity;
import model.mutation.TraitType;
import utilities.Pair;
import view.scenecontroller.simulationstrategy.SimulationViewLogics;
import view.scenecontroller.simulationstrategy.SimulationViewLogicsImpl;
import view.utilities.traitgraphs.TraitGraphs;
import view.utilities.traitgraphs.TraitGraphsImpl;

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
    private Label aspeedLbl;

    @FXML
    private Label adimensionLbl;

    @FXML
    private Label temperatureLbl;

    @FXML
    private HBox top;

    @FXML
    private HBox bottom;

    @FXML
    private VBox lateralPane;

    @FXML
    private ScrollPane scrollPane;

    private SimulationViewLogics logics;
    private final TraitGraphs graphs = new TraitGraphsImpl();

    @FXML
    private void startStop() {
        this.getView().getController().startStopSimulation();
    }

    @FXML
    private void backClick() {
        if (this.getView().getController().isSimulationRunning()) {
            this.getView().getController().startStopSimulation();
        }
        //Reset graphs.
        this.graphs.reset();
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
        final int width = (int) this.getView().getController().getEnvironmentDimension().getX();
        final int height = (int) this.getView().getController().getEnvironmentDimension().getY();
        this.getView().setSimulationController(this);
        this.logics = new SimulationViewLogicsImpl(this.canvas.getGraphicsContext2D(), width, height);
        //Create graphs.
        this.createGraphs();
        //Initialize the canvas dimension.
        this.adjustCanvas();
        this.getView().getController().startSimulation();
    }

    private void createGraphs() {
        this.graphs.load(this.lateralPane);
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
        //Update graphs with new averages.
        if (!organisms.isEmpty()) {
            final Map<TraitType, Double> averages = organisms.stream()
                    .flatMap((x) -> x.getY().getTraits().entrySet().stream())
                    .filter((x) -> !x.getKey().getRarity().equals(MutationRarity.NOMUTATION))
                    .collect(Collectors.groupingBy((x) -> x.getKey(), Collectors.averagingInt((x) -> x.getValue().getValue())));
            Platform.runLater(() -> {
                this.logics.setEntities(foods, organisms);
                this.logics.update();
                this.aliveLbl.setText(String.valueOf(this.logics.getAlive()));
                this.aspeedLbl.setText(String.format("%.2f", averages.get(TraitType.SPEED)));
                this.adimensionLbl.setText(String.format("%.2f", averages.get(TraitType.DIMENSION)));
                this.temperatureLbl.setText(String.format("%.2f", organisms.stream().findAny().get().getY().getEnvironmentKnowledge().getTemperature().getValue()));
                this.graphs.update(averages);
            });
        }
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
            final Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("INFO");
            a.setContentText("The simulation is over, everyone is dead.");
            a.show();
        });
    }
}
