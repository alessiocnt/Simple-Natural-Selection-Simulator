package view;

import java.util.Objects;
import java.util.Set;

import controller.Controller;
import javafx.stage.Stage;
import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;
import utilities.Pair;
import view.scenecontroller.SimulationController;
import view.scenefactory.SceneFactory;
import view.scenefactory.SceneFactoryImpl;

/**
 * View implementation.
 *
 */
public class ViewImpl implements View {

    private Controller controller;
    private final SceneFactory sceneFactory;
    private SimulationController simulationController;

    /**
     * @param stage
     * the stage where tha app run.
     */
    public ViewImpl(final Stage stage) {
        this.sceneFactory = new SceneFactoryImpl(stage, this);
    }

    @Override 
    public final void launch(final Controller controller) {
        this.controller = controller;
        this.sceneFactory.openSetup();
    }

    @Override
    public final Controller getController() {
        return this.controller;
    }

    @Override
    public final SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }

    @Override
    public final void render(final Set<Pair<Position, Food>> foods, final Set<Pair<Position, Organism>> organisms) {
        Objects.requireNonNull(this.simulationController);
        this.simulationController.render(foods, organisms);
    }

    @Override
    public final void setSimulationOver() {
        this.simulationController.simulationOver();
    }

    @Override
    public final void setSimulationController(final SimulationController simulationController) {
        this.simulationController = simulationController;
    }

}
