package view.observers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.control.ComboBox;
import settings.SetupValues;
import view.entities.EnvironmentHolder;

/**
 * Temperature observer.
 * 
 */
public class TemperatureObserver implements SetupObserver {

    private final ComboBox<Integer> combobox;

    /**
     * @param combobox
     * the combobox that is observed.
     */
    public TemperatureObserver(final ComboBox<Integer> combobox) {
        this.combobox = combobox;
        this.combobox.getItems().addAll(Stream.iterate(SetupValues.TEMPERATURE.getStart(),
                                        i -> i != SetupValues.TEMPERATURE.getStop() + 1,
                                        i -> i + 1)
                                        .collect(Collectors.toList()));
        this.combobox.getSelectionModel().select(SetupValues.TEMPERATURE.getDefault());
    }

    @Override
    public final void update(final EnvironmentHolder holder) {
        holder.setTemperature(this.combobox.getValue());
    }

}
