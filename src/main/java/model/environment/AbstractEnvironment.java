package model.environment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.exceptions.OutOfEnviromentException;
import model.environment.position.Position;
import model.environment.position.PositionImpl;
import utilities.Pair;

/**
 * Abstract class to describe environment.
 */
public abstract class AbstractEnvironment implements Environment {
    private static final Random RND = new Random();
    private final int xDimension;
    private final int yDimension;
    private int currentFoodQuantity = 0;
    private int currentOrganismQuantity = 0;
    private final Map<Position, Food> foods = new ConcurrentHashMap<>();
    private final Map<Organism, Position> organisms = new ConcurrentHashMap<>();

    /**
     * 
     * @param xDimension
     * x dimension.
     * @param yDimension
     * y dimension.
     */
    public AbstractEnvironment(final int xDimension, final int yDimension) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
    }

    /**
     * {@inheritDoc}
     */
    public int getCurrendOrganismQuantity() {
        return this.currentOrganismQuantity;
    }

    /**{@inheritDoc}
     */
    public int getCurrentFoodQuantity() {
        return this.currentFoodQuantity;
    }

    /**
     * {@inheritDoc}
     */
    public void addOrganism(final Organism organism) {
        Objects.requireNonNull(organism);
        Position organismPosition = this.getRandomPosition();
        this.organisms.put(organism, organismPosition);
        this.currentOrganismQuantity++;
    }

    /**
     * {@inheritDoc}
     */
   public void addOrganism(final Organism father, final Organism son) throws NoSuchElementException {
       Objects.requireNonNull(father);
       Objects.requireNonNull(son);
       Position pos = this.organisms.get(father);
       this.organisms.put(son, pos);
       this.currentOrganismQuantity++;
   }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFood(final Food food) {
        Objects.requireNonNull(food);
        //putting a food in an empty position
        Position foodPosition = this.getRandomPosition();
        this.foods.put(foodPosition, food);
        this.currentFoodQuantity++;
    }

    private Position getRandomPosition() {
        return new PositionImpl(AbstractEnvironment.RND.nextInt(this.xDimension), AbstractEnvironment.RND.nextInt(this.yDimension));
    }

    /**
     * {@inheritDoc}
     */
    public void moveOrganism(final Organism organism, final int xOffset, final int yOffset) throws OutOfEnviromentException {
        Position position = this.organisms.get(organism);
        Position newPosition = new PositionImpl(position.getX() + xOffset, position.getY() + yOffset);
        if (newPosition.getX() < 0 || newPosition.getX() > this.xDimension
                || newPosition.getY() < 0 || newPosition.getY() > this.yDimension) {
            throw(new OutOfEnviromentException());
        }
        this.organisms.put(organism, newPosition);
    }

    /**
     * {@inheritDoc}
     */
    public void removeOrganism(final Organism organism) {
        Objects.requireNonNull(organism);
        this.organisms.remove(organism);
        this.currentOrganismQuantity--;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeFood(final Food food) {
        Objects.requireNonNull(food);
        this.foods.values().removeIf(f -> f.equals(food));
        this.currentFoodQuantity--;
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<Organism> getOrganisms() {
        return new ArrayList<Organism>(this.organisms.keySet()).iterator();
    }

    /**
     * {@inheritDoc}
     */
    public Optional<Food> getFood(final Organism organism) {
        Objects.requireNonNull(organism);
        return Optional.ofNullable(this.foods.get(this.organisms.get(organism)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Food> getFood(final Position position) {
        return Optional.ofNullable(this.foods.get(position));
    }

    /**
     * @return an Entry of each Food and its position in the environment
     */
    public Set<Pair<Position, Food>> getPositionFoods() {
        Set<Pair<Position, Food>> s = this.foods.entrySet().stream()
                .map(e -> new Pair<>(e.getKey(), e.getValue()))
                .collect(Collectors.toSet());
        return s;
    }

    /**
     * @return an Entry of each Organism and its position in the environment
     */
    public Set<Pair<Position, Organism>> getPositionOrganisms() {
        return this.organisms.entrySet().stream()
                .map(e -> new Pair<>(e.getValue(), e.getKey()))
                .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.foods.clear();
        this.currentFoodQuantity = 0;
    }

    /**
     * {@inheritDoc}
     */
    public Position getDimension() {
        return new PositionImpl(this.xDimension, this.yDimension);
    }

    /**
     * A Sting representation of an AbstractEnvironment.
     */
    @Override
    public String toString() {
        return "AbstractEnvironment [xDimension=" + xDimension + ", yDimension=" + yDimension + ", currentFoodQuantity="
                + currentFoodQuantity + ", currentOrganismQuantity=" + currentOrganismQuantity + ", foods=" + foods
                + ", organisms=" + organisms + "]";
    }

}
