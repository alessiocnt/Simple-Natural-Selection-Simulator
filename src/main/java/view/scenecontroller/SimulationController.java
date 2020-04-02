package view.scenecontroller;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;

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
import view.scenecontroller.simulationstrategy.SimulationHandler;
import view.scenecontroller.simulationstrategy.SimulationInitializer;
import view.scenecontroller.simulationstrategy.SimulationViewLogics;
import view.scenecontroller.simulationstrategy.SimulationViewLogicsImpl;
import view.utilities.traitgraphs.TraitGraphs;
import view.utilities.traitgraphs.TraitGraphsImpl;

/**
 * Simulation controller.
 *
 */
public class SimulationController extends AbstractSceneController implements SimulationInitializer, SimulationHandler {

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
    @Override
    public final void initSimulationController(final double rootWidth, final double rootHeight) {
        final int width = (int) this.getView().getController().getEnvironmentDimension().getX();
        final int height = (int) this.getView().getController().getEnvironmentDimension().getY();
        this.getView().setSimulationController(this);
        this.logics = new SimulationViewLogicsImpl(this.canvas.getGraphicsContext2D(), width, height);
        //Create graphs.
        this.createGraphs();
        this.adjustCanvas(rootWidth, rootHeight);
        this.getView().getController().startSimulation();
    }

    private void createGraphs() {
        this.scrollPane.setContent(this.lateralPane);
        this.graphs.load(this.lateralPane);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.autosize();
    }

    @Override
    public final void render(final Set<ImmutablePair<Position, Food>> foods, final Set<ImmutablePair<Position, Organism>> organisms) {
        //Update graphs with new averages.
        if (!organisms.isEmpty()) {
            final Map<TraitType, Double> averages = organisms.stream()
                    .flatMap((x) -> x.getValue().getTraits().entrySet().stream())
                    .filter((x) -> !x.getKey().getRarity().equals(MutationRarity.NOMUTATION))
                    .collect(Collectors.groupingBy((x) -> x.getKey(), Collectors.averagingInt((x) -> x.getValue().getValue())));
            Platform.runLater(() -> {
                this.logics.setEntities(foods, organisms);
                this.logics.update();
                this.aliveLbl.setText(String.valueOf(this.logics.getAlive()));
                this.aspeedLbl.setText(String.format("%.2f", averages.get(TraitType.SPEED)));
                this.adimensionLbl.setText(String.format("%.2f", averages.get(TraitType.DIMENSION)));
                this.temperatureLbl.setText(String.format("%.2f", organisms.stream().findAny().get().getValue().getEnvironmentKnowledge().getTemperature().getValue()));
                this.graphs.update(averages);
            });
        }
    }

    @Override
    public final void adjustCanvas(final double rootWidth, final double rootHeight) {
         this.canvas.setWidth(rootWidth - this.scrollPane.getWidth());
         this.canvas.setHeight(rootHeight - this.top.getHeight() - this.bottom.getHeight());
         this.logics.setCanvasDimension(this.canvas.getWidth(), this.canvas.getHeight());
         this.logics.update();
    }

    @Override
    public final void simulationOver() {
        Platform.runLater(() -> {
            final Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("INFO");
            a.setContentText("The simulation is over, everyone is dead.");
            a.show();
        });
    }
}
