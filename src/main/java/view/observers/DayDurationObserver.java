package view.observers;

import javafx.scene.control.ComboBox;
import settings.DayDuration;
import view.View;

/**
 * Day Duration observer.
 */
public class DayDurationObserver implements SettingsObserver {

    private final ComboBox<DayDuration> combobox;

    /**
     * @param combobox
     * the combobox to be observed.
     */
    public DayDurationObserver(final ComboBox<DayDuration> combobox) {
        this.combobox = combobox;
        this.combobox.getItems().addAll(DayDuration.values());
        this.combobox.getSelectionModel().select(DayDuration.getDefualt());
    }

    @Override
    public final void update(final View view) {
        view.getController().setDayDuration(this.combobox.getSelectionModel().getSelectedItem());
    }

}
