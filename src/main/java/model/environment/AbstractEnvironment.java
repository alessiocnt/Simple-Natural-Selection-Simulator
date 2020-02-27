package model.enviroment;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import model.entity.Position;
import model.entity.food.Food;

public abstract class AbstractEnviroment implements Enviroment {
//TODO implements methods that need the organism interface to work
    private final Map<Position, Food> foods = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFood(final Food food) {
        Objects.requireNonNull(food);
        this.foods.put(food.getPosition(), food);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeFood(final Food food) {
        Objects.requireNonNull(food);
        this.foods.remove(food.getPosition());
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
    }

}
