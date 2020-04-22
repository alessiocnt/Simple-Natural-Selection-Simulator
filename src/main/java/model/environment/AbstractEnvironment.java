package model.environment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;

import model.entity.EnergyImpl;
import model.entity.food.Food;
import model.entity.food.FoodBuilderImpl;
import model.entity.organism.Organism;
import model.environment.exceptions.OutOfEnviromentException;
import model.environment.position.Position;
import model.environment.position.PositionImpl;

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
     * @param width
     *      environment width
     * @param height
     *      environment height
     */
    public AbstractEnvironment(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public final int getCurrentOrganismQuantity() {
        return this.organisms.keySet().size();
    }

    @Override
    public final int getCurrentFoodQuantity() {
        return this.foods.values().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addOrganism(final Organism organism) {
        Objects.requireNonNull(organism);
        final Position organismPosition = this.getRandomPosition();
        this.organisms.put(organism, organismPosition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
   public void addOrganism(final Organism father, final Organism son) {
       Objects.requireNonNull(father);
       Objects.requireNonNull(son);
       final Position pos = this.organisms.get(father);
       this.organisms.put(son, pos);
   }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFood(final Food food) {
        Objects.requireNonNull(food);
        //putting a food in an empty position
        final Position foodPosition = this.getRandomPosition();
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
    @Override
    public void moveOrganism(final Organism organism, final int xOffset, final int yOffset) throws OutOfEnviromentException {
        final Position position = this.organisms.get(organism);
        final Position newPosition = new PositionImpl(position.getX() + xOffset, position.getY() + yOffset);
        this.checkPosition(newPosition);
        this.organisms.put(organism, newPosition);
    }

    /**
     * Template method that checks if the new position is legal for the environment.
     * @param position
     *      the position that will be checked
     * @throws OutOfEnviromentException
     *      if the new position is outside the environment
     * @return
     *      true if the position is valid else if it's not valid
     */
    protected abstract boolean checkPosition(Position position) throws OutOfEnviromentException;

    @Override
    public final void removeOrganism(final Organism organism) {
        Objects.requireNonNull(organism);
        this.organisms.remove(organism);
    }

    @Override
    public final void removeFood(final Food food) {
        Objects.requireNonNull(food);
        this.foods.values().removeIf(f -> f.equals(food));
    }

    @Override
    public final Iterator<Organism> getOrganisms() {
        return new ArrayList<Organism>(this.organisms.keySet()).iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Food> getFood(final Organism organism) {
        Objects.requireNonNull(organism);
        return Optional.ofNullable(this.foods.get(this.organisms.get(organism)));
    }

    @Override
    public final Set<ImmutablePair<Position, Food>> getPositionFoods() {
        return this.foods.entrySet().stream()
                .map(e -> ImmutablePair.of(e.getKey(), e.getValue()))
                .collect(Collectors.toSet());
    }

    @Override
    public final Set<ImmutablePair<Position, Organism>> getPositionOrganisms() {
        return this.organisms.entrySet().stream()
                .map(e -> ImmutablePair.of(e.getValue(), e.getKey()))
                .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.foods.clear();
    }

    @Override
    public final Position getDimension() {
        return new PositionImpl(this.width, this.height);
    }

    /**
     * @return the environment foods
     */
    protected final Map<Position, Food> getFoodsMap() {
        return this.foods;
    }

    /**
     * @return the environment organisms
     */
    protected final Map<Organism, Position> getOrganismsMap() {
        return this.organisms;
    }

    /**
     * A Sting representation of an AbstractEnvironment.
     */
    @Override
    public String toString() {
        return "AbstractEnvironment [xDimension=" + width + ", yDimension=" + height + ", currentFoodQuantity="
                + this.getCurrentFoodQuantity() + ", currentOrganismQuantity=" + this.getCurrentOrganismQuantity() + ", foods=" + foods
                + ", organisms=" + organisms + "]";
    }

}
