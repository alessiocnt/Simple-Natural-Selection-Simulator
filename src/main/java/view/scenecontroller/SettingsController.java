package view.scenecontroller;

import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import settings.DayDuration;
import view.observers.DayDurationObserver;
import view.observers.SettingsObserver;
import javafx.scene.control.Button;

/**
 * Settings controller.
 *
 */
public class SettingsController extends AbstractSceneController {

    @FXML
    private ComboBox<DayDuration> dayDurComboBox;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    private final List<SettingsObserver> observers = new LinkedList<>();

    /**
     * Initialize the UI.
     */
    @FXML
    public void initialize() {
        this.observers.clear();
        this.observers.addAll(List.of(new DayDurationObserver(this.dayDurComboBox)));
    }

    @FXML
    private void exitSettings() {
        this.getView().getSceneFactory().openSimulation();
    }

    @FXML
    private void saveSettings() {
        this.observers.forEach((observer) -> observer.update(this.getView()));
        this.getView().getSceneFactory().openSimulation();
    }
}
