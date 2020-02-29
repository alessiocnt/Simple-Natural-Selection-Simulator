package application;

import controller.Controller;
import controller.ControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelImpl;
import view.View;
import view.ViewImpl;

/**
 * Simulator main.
 *
 */
public class Main extends Application {
    /**
     * @param args
     * params.
     */
    public static void main(final String... args) {
        launch(args);
    }

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final View view = new ViewImpl(primaryStage);
        final Controller controller = new ControllerImpl(new ModelImpl(), view);
        view.launch(controller);
        //view.render(null, null);
    }

}
