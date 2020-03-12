package model.environment;

import java.util.Set;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.temperature.Temperature;

/**
 * The Advanced Environment implementation.
 */
public class AdvancedEnvironmentImpl extends BasicEnvironmentImpl implements AdvancedEnvironment, OrganismEnvironmentHolder {

    private Temperature temperature;

    protected AdvancedEnvironmentImpl(final int xDimension, final int yDimension, final int morningFoodQuantity,
            final int dailyFoodQuantityModification, final Temperature temperature) {
        super(xDimension, yDimension, morningFoodQuantity, dailyFoodQuantityModification);
        this.temperature = temperature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Temperature getTemperature() {
        return this.temperature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Food> getNearbyFoods(final Organism organism) {
        // TODO Auto-generated method stub
        return null;
    }

}
