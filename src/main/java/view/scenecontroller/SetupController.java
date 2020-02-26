package view.scenecontroller;

import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import view.observers.DimensionObserver;
import view.observers.EntityQuantityObserver;
import view.observers.FoodQuantityObserver;
import view.observers.FoodVariationObserver;
import view.observers.SetupObserver;
import view.observers.SpeedObserver;

/**
 * Environment Setup scene controller.
 *
 */
public class SetupController extends AbstractSceneController {
    @FXML
    private Button startBtn;

    @FXML
    private Button startDefaultBtn;

    @FXML
    private ComboBox<Double> dimComboBox;

    @FXML
    private ComboBox<Double> speedComboBox;

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
        this.observers.addAll(List.of(new DimensionObserver(this.dimComboBox),
                new SpeedObserver(this.speedComboBox),
                new FoodQuantityObserver(this.foodQComboBox),
                new FoodVariationObserver(this.foodVComboBox),
                new EntityQuantityObserver(this.quantityComboBox)));
    }
}
