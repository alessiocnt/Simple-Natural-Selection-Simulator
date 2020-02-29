package view.scenecontroller.simulationstrategy;

import java.util.Map.Entry;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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
    private double xAxisScaleFactor;
    private double yAxisScaleFactor;
    private double canvasWidth;
    private double canvasHeight;


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
        this.xAxisScaleFactor = getXScaleFactor();
        this.yAxisScaleFactor = getYScaleFactor();
    }


    private double getXScaleFactor() {
        return this.canvasWidth / this.environmentX;
    }

    private double getYScaleFactor() {
        return this.canvasHeight / this.environmentY;
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
    public void update() {
        this.graphics.setFill(Color.BEIGE);
        this.graphics.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
        this.graphics.fill();
        // TODO when ever
        for (Entry<Position, Food> entry : foods) {
            this.graphics.setFill(Color.DARKGREEN);
            this.graphics.fillRect(entry.getKey().getX() * this.xAxisScaleFactor, 
                    entry.getKey().getY() * this.yAxisScaleFactor, this.xAxisScaleFactor, this.yAxisScaleFactor);
        }

        for (Entry<Position, Organism> entry : organisms) {
            this.graphics.setFill(getOrganismColor(entry.getValue()));
            this.graphics.fillOval(entry.getKey().getX() * this.xAxisScaleFactor, entry.getKey().getY() * this.yAxisScaleFactor, 
                    this.xAxisScaleFactor * getOrganismDimension(entry.getValue()), this.yAxisScaleFactor * getOrganismDimension(entry.getValue()));
        }

        // colore organism
        this.graphics.setFill(Color.RED);
        this.graphics.fillOval(0, 0, 50, 50);
        // colore food
        this.graphics.setFill(Color.DARKGREEN);
        this.graphics.fillRect(65, 23, 25, 25);
    }

    private Color getOrganismColor(final Organism organism) {
        final int speedMult = 50;
        final int childMult = 20;
        return Color.rgb(organism.getTraits().get(TraitType.SPEED).getValue() * speedMult, 0, 
                organism.getTraits().get(TraitType.CHILDRENQUANTITY).getValue() * childMult);
    }


    private double getOrganismDimension(final Organism organism) {
        return organism.getTraits().get(TraitType.DIMENSION).getValue() / 100;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanvasDimension(final double width, final double height) {
        this.canvasWidth = width;
        this.canvasHeight = height;
        this.xAxisScaleFactor = getXScaleFactor();
        this.yAxisScaleFactor = getYScaleFactor();
    }
}
