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
public class DimensionObserver implements SetupObserver{

    private final ComboBox<Double> comboBox;

    /**
     * @param comboBox
     * the combobox that is observed
     */
    public DimensionObserver(final ComboBox<Double> comboBox) {
        this.comboBox = comboBox;
        this.comboBox.getItems().addAll(Stream.iterate(SetupValues.DIMENSION.getStart(), (i) -> i + 1)
                .limit(SetupValues.DIMENSION.getStop())
                .map((i) -> (double) i)
                .collect(Collectors.toList()));
    }

    @Override
    public final void update(final EnvironmentHolder holder) {
        holder.setEntityDimension(this.comboBox.getValue());
    }
}
