package view.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

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

    /**
     * @param title
     * Title of the dialog
     * @param text
     * Text to display.
     */
    public static void showHelp(final String title, final String text) {
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Natural Selection Simulator");

        //Create textArea to host the help text.
        final TextArea textArea = new TextArea(text);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        final GridPane content = new GridPane();
        content.setMaxWidth(Double.MAX_VALUE);
        content.add(textArea, 0, 0);

        //Set help text
        alert.getDialogPane().setContent(content);
        //Show the alert
        alert.showAndWait();
    }
}
