package view;

import java.util.Map.Entry;
import java.util.Set;

import controller.Controller;
import javafx.stage.Stage;
import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;
import view.scenecontroller.SimulationController;
import view.scenefactory.SceneFactory;
import view.scenefactory.SceneFactoryImpl;

/**
 * View implementation.
 *
 */
public class ViewImpl implements View {

    private final Stage stage;
    private Controller controller;
    private final SceneFactory sceneFactory;
    private SimulationController simulationController;

    /**
     * @param stage
     * the stage where tha app run.
     */
    public ViewImpl(final Stage stage) {
        this.stage = stage;
        this.sceneFactory = new SceneFactoryImpl(this.stage, this);
        this.simulationController = new SimulationController();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Set<Entry<Position, Food>> foods, final Set<Entry<Position, Organism>> organisms) {
        this.simulationController.render(foods, organisms);
    }

}
