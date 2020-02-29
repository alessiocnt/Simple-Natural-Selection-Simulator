package view.scenecontroller;

import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;

import java.util.Map.Entry;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;
import view.scenecontroller.simulationstrategy.SimulationViewLogics;
import view.scenecontroller.simulationstrategy.SimulationViewLogicsImpl;

/**
 * Simulation controller.
 *
 */
public class SimulationController extends AbstractSceneController {

    @FXML
    private Canvas canvas;


    private SimulationViewLogics logics;


    public SimulationController() {
        System.out.println(canvas);
        //this.logics = new SimulationViewLogicsImpl(this.canvas.getGraphicsContext2D(), 100, 100);
                /* Non abbiamo ancora questi 2 metodi nel controller, per le prove fare 100, 100
                 * this.getView().getController().getEnvironmentX(), this.getView().getController().getEnvironmentY());*/
    }
    /**
     * Updates the canvas with Environment parameters.
     * @param foods
     *      food that will be displayed
     * @param organisms
     *      organisms that will be displayed
     */
    public void render(final Set<Entry<Position, Food>> foods, final Set<Entry<Position, Organism>> organisms) {
        //this.logics.setCanvasDimension(Toolkit.getDefaultToolkit().getScreenSize());
        this.logics.setEntities(foods, organisms);
    }
}
