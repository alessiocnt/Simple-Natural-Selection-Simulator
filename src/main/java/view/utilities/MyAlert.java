package view.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Utility class that holds the creation and the show of a simple alert.
 *
 */
public final class MyAlert {

    private MyAlert() {
    }

    /**
     * @param type
     * type of alert
     * @param title
     * title of the alert
     * @param text
     * text to be visualized
     */
    public static void showAlert(final AlertType type, final String title, final String text) {
        final Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
