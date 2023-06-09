package view.observers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.control.ComboBox;
import settings.SetupValues;
import view.entities.EnvironmentHolder;

/**
 * Food Variation Observer.
 *
 */
public class FoodVariationObserver implements SetupObserver {

    private final ComboBox<Integer> combobox;

    /**
     * @param combobox
     * the combobox that is observed.
     */
    public FoodVariationObserver(final ComboBox<Integer> combobox) { 
        this.combobox = combobox;
        this.combobox.getItems().addAll(Stream.iterate(SetupValues.FOODVARIATION.getStart(),
                                        i -> i != SetupValues.FOODVARIATION.getStop() + 1,
                                        i -> i + 1)
                                        .collect(Collectors.toList()));
        this.combobox.getSelectionModel().select(SetupValues.FOODVARIATION.getDefault());
    }

    @Override
    public final void update(final EnvironmentHolder holder) {
        holder.setFoodVariation(this.combobox.getValue());
    }
}
