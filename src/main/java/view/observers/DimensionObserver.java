package view.observers;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.control.ComboBox;
import settings.SetupValues;
import view.entities.EnvironmentHolder;

/**
 * Dimension observer.
 *
 */
public class DimensionObserver implements SetupObserver {

    private final ComboBox<Integer> combobox;

    /**
     * @param combobox
     * the combobox that is observed
     */
    public DimensionObserver(final ComboBox<Integer> combobox) {
        this.combobox = combobox;
        this.combobox.getItems().addAll(Stream.iterate(SetupValues.DIMENSION.getStart(),
                                        (i) -> i != SetupValues.DIMENSION.getStop() + 1,
                                        (i) -> i + 1)
                                        .collect(Collectors.toList()));
        this.combobox.getSelectionModel().select(SetupValues.DIMENSION.getDefault());
    }

    @Override
    public final void update(final EnvironmentHolder holder) {
        holder.setEntityDimension(this.combobox.getValue());
    }
}
