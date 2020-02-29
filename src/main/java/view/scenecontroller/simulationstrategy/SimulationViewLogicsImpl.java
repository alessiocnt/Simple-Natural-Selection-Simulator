package view.scenecontroller.simulationstrategy;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map.Entry;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;
import model.mutation.TraitType;

/**
 * Class that defines the logic to display entities on screen.
 */
public class SimulationViewLogicsImpl implements SimulationViewLogics {

    private Set<Entry<Position, Food>> foods;
    private Set<Entry<Position, Organism>> organisms;
    private final GraphicsContext graphics;
    private final int environmentX;
    private final int environmentY;
    private final double entityX;
    private final double entityY;
    private Dimension screenSize;


    /**
     * Creates a new SimulationViewLogicsImpl.
     * @param graphics
     *      the Canvas graphics context
     * @param environmentX
     *      x-axis dimension of the environment
     * @param environmentY
     *      y-axis dimension of the environment
     */
    public SimulationViewLogicsImpl(final GraphicsContext graphics, final int environmentX, final int environmentY) {
        this.graphics = graphics;
        this.environmentX = environmentX;
        this.environmentY = environmentY;
        this.entityX = getEntityX();
        this.entityY = getEntityY();
    }


    private double getEntityX() {
        return this.screenSize.getWidth() / this.environmentX;
    }

    private double getEntityY() {
        return this.screenSize.getHeight() / this.environmentY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntities(final Set<Entry<Position, Food>> foods, final Set<Entry<Position, Organism>> organisms) {
        this.foods = foods;
        this.organisms = organisms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicsContext getUpdatedGraphicsContext() {
        return update();
    }

    private GraphicsContext update() {
        /*for (Entry<Position, Food> entry : foods) {
            this.graphics.setFill(Color.DARKGREEN);
            this.graphics.fillRect(entry.getKey().getX(), entry.getKey().getY(), this.entityX, this.entityY);
        }

        for (Entry<Position, Organism> entry : organisms) {
            this.graphics.setFill(getOrganismColor(entry.getValue()));
            this.graphics.fillOval(entry.getKey().getX(), entry.getKey().getY(), 
                    this.entityX * getOrganismDimension(entry.getValue()), this.entityY * getOrganismDimension(entry.getValue()));
        }*/

        // colore organism
        this.graphics.setFill(Color.RED);
        this.graphics.fillOval(0, 0, 50, 50);
        // colore food
        this.graphics.setFill(Color.DARKGREEN);
        this.graphics.fillRect(65, 23, 25, 25);

        return this.graphics;
    }

    private Color getOrganismColor(final Organism organism) {
        // TODO fallo con strategy, per ora è solo una prova per colorare
        return Color.rgb(organism.getTraits().get(TraitType.SPEED).getValue() * 50, 0, 0);
    }


    private double getOrganismDimension(final Organism o) {
        return o.getTraits().get(TraitType.DIMENSION).getValue() / 100 / 2;
    }

    /**
     * Tells logics what is the canvas dimensions.
     * @param screenSize
     *      the screenSize
     */
    @Override
    public void setCanvasDimension(final Dimension screenSize) {
        this.screenSize = screenSize;
    }




}
