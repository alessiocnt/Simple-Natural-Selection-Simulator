package view.observers;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import view.entities.EnvironmentHolder;
import view.utilities.MyAlert;

/**
 * Dimension observer.
 *
 */
public class DimensionObserver implements SetupObserver{

    private final TextField textField;

    /**
     * @param textField
     * the textField that is observed
     */
    public DimensionObserver(final TextField textField) {
        this.textField = textField;
    }

    @Override
    public final void update(final EnvironmentHolder holder) {
        if (!this.validate()) {
            throw new IllegalStateException();
        }
        holder.setEntityDimension(Double.valueOf(this.textField.getText()));
    }

    @Override
    public final boolean validate() {
        try {
            Double.valueOf(this.textField.getText());
        } catch (NumberFormatException e) {
            MyAlert.showAlert(AlertType.ERROR, "Dimension error", "Please insert a number in the proper form");
            return false;
        }
        return true;
    }
}
