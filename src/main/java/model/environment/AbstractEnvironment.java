package model.environment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.exceptions.OutOfEnviromentException;
import model.environment.position.Position;
import model.environment.position.PositionImpl;

public abstract class AbstractEnvironment implements Environment {
//TODO what if the environment is full?
    private static final Random RND = new Random();
    private final int xDimension;
    private final int yDimension;
    private int currentFoodQuantity = 0;
    private int currentOrganismQuantity = 0;
    private final Map<Position, Food> foods = new HashMap<>();
    private final Map<Position, Set<Organism>> organisms = new HashMap<>();

    /**
     * 
     * @param xDimension
     * @param yDimension
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
        Position organismPosition = this.getEmptyPosition();
        Set<Organism> value = new HashSet<>();
        value.add(organism);
        this.organisms.put(organismPosition, value);
        this.currentOrganismQuantity++;
    }

    /**
     * {@inheritDoc}
     */
   public void addOrganism(final Organism father, final Organism son) throws NoSuchElementException {
       Objects.requireNonNull(father);
       Objects.requireNonNull(son);
       Position pos = findOrganismPosition(father);
       Set<Organism> s = this.organisms.get(pos);
       s.add(son);
       this.currentOrganismQuantity++;
   }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFood(final Food food) {
        Objects.requireNonNull(food);
        //putting a food in an empty position
        Position foodPosition = this.getEmptyPosition();
        this.foods.put(foodPosition, food);
        this.currentFoodQuantity++;
    }

    private Position getEmptyPosition() {
        Position ret;
        do {
            ret = new PositionImpl(RND.nextInt(this.xDimension), RND.nextInt(this.yDimension));
        } while (this.foods.get(ret) != null && this.organisms.get(ret) != null);
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    public void moveOrganism(final Organism organism, final int xOffset, final int yOffset) throws OutOfEnviromentException, NoSuchElementException {
        Position position = findOrganismPosition(organism);
        Position newPosition = new PositionImpl(position.getX() + xOffset, position.getY() + yOffset);
        if (newPosition.getX() < 0 || newPosition.getX() > this.xDimension
                || newPosition.getY() < 0 || newPosition.getY() > this.yDimension) {
            throw(new OutOfEnviromentException());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeOrganism(final Organism organism) {
        Objects.requireNonNull(organism);
        this.organisms.values().stream()
                .filter(orgSet -> orgSet.contains(organism))
                .collect(Collectors.toList())
                .get(0).remove(organism);
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
        Set<Organism> org = new HashSet<>();
        this.organisms.values().forEach(org::addAll);
        return org.iterator();
    }

    /**
     * {@inheritDoc}
     */
    public Optional<Set<Organism>> getOrganisms(final Position position) {
        return Optional.of(this.organisms.get(position));
    }
    /**
     * {@inheritDoc}
     */
    public Optional<Food> getFood(final Organism organism) throws NoSuchElementException {
        Position position = findOrganismPosition(organism);
        return Optional.ofNullable(this.foods.get(position));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Food> getFood(final Position position) {
        return Optional.ofNullable(this.foods.get(position));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.foods.clear();
        this.currentFoodQuantity = 0;
    }

    private Position findOrganismPosition(final Organism organism) {
        //TODO find a better way to get the Position
        Position position = new PositionImpl(-1, -1);
        this.organisms.forEach((k, v) -> {
            if (v.contains(organism)) {
                position.setPosition(k.getX(), k.getY());
            }
        });
        if (position.getX() == -1 && position.getY() == -1) {
            throw(new NoSuchElementException(organism + " not found"));
        }
        return position;
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
