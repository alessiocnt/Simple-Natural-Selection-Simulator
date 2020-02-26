package view.observers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.control.ComboBox;
import settings.SetupValues;
import view.entities.EnvironmentHolder;

/**
 * Entity Quantity Observer.
 *
 */
public class EntityQuantityObserver implements SetupObserver {

    private final ComboBox<Integer> combobox;

    /**
     * @param combobox
     * combobox that is observed.
     */
    public EntityQuantityObserver(final ComboBox<Integer> combobox) {
        this.combobox = combobox;
        this.combobox.getItems().addAll(Stream.iterate(SetupValues.INITIALQUANTITY.getStart(),
                                                        (i) -> i != SetupValues.INITIALQUANTITY.getStop() + 1,
                                                        (i) -> i + 1)
        .collect(Collectors.toList()));
        this.combobox.getSelectionModel().select(SetupValues.INITIALQUANTITY.getDefault());
    }

    @Override
    public final void update(final EnvironmentHolder holder) {
        holder.setEntityQuantity(this.combobox.getValue());
    }

}
