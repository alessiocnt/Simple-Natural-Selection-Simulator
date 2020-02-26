package view.observers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.control.ComboBox;
import settings.SetupValues;
import view.entities.EnvironmentHolder;

/**
 * Speed Observer.
 *
 */
public class SpeedObserver implements SetupObserver {

    private final ComboBox<Double> combobox;

    /**
     * @param combobox
     * the combobox that is observed
     */
    public SpeedObserver(final ComboBox<Double> combobox) {
        this.combobox = combobox;
        this.combobox.getItems().addAll(Stream.iterate(SetupValues.SPEED.getStart(),
                                                        (i) -> i != SetupValues.SPEED.getStop() + 1,
                                                        (i) -> i + 1)
                .map((i) -> (double) i)
                .collect(Collectors.toList()));
        this.combobox.getSelectionModel().select((double) SetupValues.SPEED.getDefault());
    }

    @Override
    public final void update(final EnvironmentHolder holder) {
        holder.setEntitySpeed(this.combobox.getValue());
    }

}
