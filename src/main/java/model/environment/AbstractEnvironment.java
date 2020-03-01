package model.environment;

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

public abstract class AbstractEnvironment implements Environment {
//TODO what if the environment is full?
    private static final Random RND = new Random();
    private final int xDimension;
    private final int yDimension;
    private int currentFoodQuantity = 0;
    private int currentOrganismQuantity = 0;
    private final Map<Position, Food> foods = new ConcurrentHashMap<>();
    private final Map<Position, Organism> organisms = new ConcurrentHashMap<>();

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
        this.organisms.put(organismPosition, organism);
        this.currentOrganismQuantity++;
    }

    /**
     * {@inheritDoc}
     */
   public void addOrganism(final Organism father, final Organism son) throws NoSuchElementException {
       Objects.requireNonNull(father);
       Objects.requireNonNull(son);
       Position pos = this.getEmptyPosition();
       this.organisms.put(pos, son);
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
                || newPosition.getY() < 0 || newPosition.getY() > this.yDimension || this.organisms.get(newPosition) != null) {
            System.out.println(newPosition);
            throw(new OutOfEnviromentException());
        }
        System.out.println("Removing " + organism);
        this.removeOrganism(organism);
        System.out.println("Adding " + organism);
        this.organisms.put(newPosition, organism);
    }

    /**
     * {@inheritDoc}
     */
    public void removeOrganism(final Organism organism) {
        Objects.requireNonNull(organism);
        Iterator<Organism> i = this.getOrganisms();
        while (i.hasNext()) {
            if (i.next().equals(organism)) {
                i.remove();
                break;
            }
        }
        this.organisms.values().removeIf(value -> value.equals(organism));
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
        return this.organisms.values().iterator();
    }

    /**
     * {@inheritDoc}
     */
    public Optional<Organism> getOrganisms(final Position position) {
        return Optional.ofNullable(this.organisms.get(position));
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
                .map(e -> new Pair<>(e.getKey(), e.getValue()))
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

    private Position findOrganismPosition(final Organism organism) {
        //TODO find a better way to get the Position
        Position position = new PositionImpl(-1, -1);
        this.organisms.forEach((k, v) -> {
            if (v.equals(organism)) {
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
