package view.observers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.control.ComboBox;
import settings.SetupValues;
import view.entities.EnvironmentHolder;

/**
 * Food Quantity observer.
 *
 */
public class FoodQuantityObserver implements SetupObserver {

    private final ComboBox<Integer> combobox;

    /**
     * @param combobox
     * combobox that is observed.
     */
    public FoodQuantityObserver(final ComboBox<Integer> combobox) {
        this.combobox = combobox;
        this.combobox.getItems().addAll(Stream.iterate(SetupValues.FOODQUANTITY.getStart(), (i) -> i + 1)
                .limit(SetupValues.FOODQUANTITY.getStop())
                .collect(Collectors.toList()));
    }

    @Override
    public final void update(final EnvironmentHolder holder) {
        holder.setFoodQuantity(this.combobox.getValue());
    }
}
