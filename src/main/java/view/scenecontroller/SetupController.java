package view.scenecontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import view.entities.EnvironmentHolder;
import view.observers.DimensionObserver;
import view.observers.EntityQuantityObserver;
import view.observers.FoodQuantityObserver;
import view.observers.FoodVariationObserver;
import view.observers.SetupObserver;
import view.observers.SpeedObserver;
import view.observers.TemperatureObserver;
import view.utilities.MyAlert;

/**
 * Environment Setup scene controller.
 *
 */
public class SetupController extends AbstractSceneController {
    @FXML
    private Button startBtn;

    @FXML
    private Button restoreDefaultBtn;

    @FXML
    private Button helpBtn;

    @FXML
    private ComboBox<Integer> dimComboBox;

    @FXML
    private ComboBox<Integer> speedComboBox;

    @FXML
    private ComboBox<Integer> foodQComboBox;

    @FXML
    private ComboBox<Integer> foodVComboBox;

    @FXML
    private ComboBox<Integer> quantityComboBox;

    @FXML
    private ComboBox<Integer> temperatureComboBox;

    private final List<SetupObserver> observers = new LinkedList<>();

    /**
     * Initialize the UI. 
     */
    @FXML
    public void initialize() {
        this.observers.clear();
        this.observers.addAll(List.of(new DimensionObserver(this.dimComboBox),
                new SpeedObserver(this.speedComboBox),
                new FoodQuantityObserver(this.foodQComboBox),
                new FoodVariationObserver(this.foodVComboBox),
                new EntityQuantityObserver(this.quantityComboBox),
                new TemperatureObserver(this.temperatureComboBox)));
    }

    @FXML
    private void startSimulation() {
        final EnvironmentHolder holder = new EnvironmentHolder();
        //Imposto l'holder attraverso tutti gli observer
        this.observers.forEach(observer -> observer.update(holder));
        this.getView().getController().setEnvironmentInitialValues(holder);
        this.getSceneFactory().openSimulation();
    }

    @FXML
    private void restoreDefault() {
        this.initialize();
    }

    @FXML
    private void helpClicked() throws URISyntaxException {
        try {
            final String fileName = "documents/helpText.txt";
            try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
                 BufferedReader br = new BufferedReader(new InputStreamReader(in));) {
                final StringBuilder builder = new StringBuilder();
                br.lines().forEach((string) -> builder.append(string + "\n"));
                final String text = builder.toString();
                MyAlert.showHelp("Help", text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
