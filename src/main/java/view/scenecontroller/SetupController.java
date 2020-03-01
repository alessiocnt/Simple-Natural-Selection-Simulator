package view.scenecontroller;

import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import view.entities.EnvironmentHolder;
import view.observers.DimensionObserver;
import view.observers.EntityQuantityObserver;
import view.observers.FoodQuantityObserver;
import view.observers.FoodVariationObserver;
import view.observers.SetupObserver;
import view.observers.SpeedObserver;
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
                new EntityQuantityObserver(this.quantityComboBox)));
    }

    @FXML
    private void startSimulation() {
        final EnvironmentHolder holder = new EnvironmentHolder();
        //Imposto l'holder attraverso tutti gli observer
        this.observers.forEach((observer) -> observer.update(holder));
        MyAlert.showAlert(AlertType.INFORMATION, "START", holder.toString());
        this.getView().getController().setEnvironmentInitialValues(holder);

        //TODO per ora dará errore perche non é stata implementata la parte nel Controller!
        if (this.getView().getController().initSimulation()) {
            //Initialization is ok, so open the simulation scene.
            this.getSceneFactory().openSimulation();
            this.getView().getController().startSimulation();
        } else {
            MyAlert.showAlert(AlertType.ERROR, "Error", "Something went wrong, retry!");
        }
    }

    @FXML
    private void restoreDefault() {
        this.initialize();
    }
}
