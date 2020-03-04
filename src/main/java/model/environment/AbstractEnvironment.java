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

import model.entity.EnergyImpl;
import model.entity.food.Food;
import model.entity.food.FoodBuilderImpl;
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
    private final int width;
    private final int height;
    private final Map<Position, Food> foods = new ConcurrentHashMap<>();
    private final Map<Organism, Position> organisms = new ConcurrentHashMap<>();

    /**
     * 
     * @param width
     *      environment width
     * @param height
     *      environment height
     */
    public AbstractEnvironment(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    public int getCurrendOrganismQuantity() {
        return this.organisms.keySet().size();
    }

    /**{@inheritDoc}
     */
    public int getCurrentFoodQuantity() {
        return this.foods.values().size();
    }

    /**
     * {@inheritDoc}
     */
    public void addOrganism(final Organism organism) {
        Objects.requireNonNull(organism);
        Position organismPosition = this.getRandomPosition();
        this.organisms.put(organism, organismPosition);
    }

    /**
     * {@inheritDoc}
     */
   public void addOrganism(final Organism father, final Organism son) throws NoSuchElementException {
       Objects.requireNonNull(father);
       Objects.requireNonNull(son);
       Position pos = this.organisms.get(father);
       this.organisms.put(son, pos);
   }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFood(final Food food) {
        Objects.requireNonNull(food);
        //putting a food in an empty position
        Position foodPosition = this.getRandomPosition();
        this.foods.put(foodPosition, this.foods.get(foodPosition) == null ? food 
                : new FoodBuilderImpl().setEnergy(
                    new EnergyImpl(this.foods.get(foodPosition).getEnergy().getEnergy() + food.getEnergy().getEnergy())
                ).build());
    }

    private Position getRandomPosition() {
        return new PositionImpl(AbstractEnvironment.RND.nextInt(this.width), AbstractEnvironment.RND.nextInt(this.height));
    }

    /**
     * {@inheritDoc}
     */
    public void moveOrganism(final Organism organism, final int xOffset, final int yOffset) throws OutOfEnviromentException {
        Position position = this.organisms.get(organism);
        Position newPosition = new PositionImpl(position.getX() + xOffset, position.getY() + yOffset);
        if (newPosition.getX() < 0 || newPosition.getX() > this.width
                || newPosition.getY() < 0 || newPosition.getY() > this.height) {
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeFood(final Food food) {
        Objects.requireNonNull(food);
        this.foods.values().removeIf(f -> f.equals(food));
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
    }

    /**
     * {@inheritDoc}
     */
    public Position getDimension() {
        return new PositionImpl(this.width, this.height);
    }

    /**
     * A Sting representation of an AbstractEnvironment.
     */
    @Override
    public String toString() {
        return "AbstractEnvironment [xDimension=" + width + ", yDimension=" + height + ", currentFoodQuantity="
                + this.getCurrentFoodQuantity() + ", currentOrganismQuantity=" + this.getCurrendOrganismQuantity() + ", foods=" + foods
                + ", organisms=" + organisms + "]";
    }

}
