package model.environment;

import java.util.HashSet;
import java.util.Set;

import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.position.Position;
import model.environment.position.PositionImpl;
import model.environment.temperature.Temperature;
import model.mutation.TraitType;

/**
 * The Advanced Environment implementation.
 */
public class AdvancedEnvironmentImpl extends BasicEnvironmentImpl implements AdvancedEnvironment {

    private final Temperature temperature;

    /**
     * @param xDimension
     * environment width.
     * @param yDimension
     * environment height.
     * @param morningFoodQuantity
     * morning food quantity.
     * @param dailyFoodQuantityModification
     * daily food variation.
     * @param temperature
     * temperature of the environment.
     */
    public AdvancedEnvironmentImpl(final int xDimension, final int yDimension, final int morningFoodQuantity,
            final int dailyFoodQuantityModification, final Temperature temperature) {
        super(xDimension, yDimension, morningFoodQuantity, dailyFoodQuantityModification);
        this.temperature = temperature;
    }

    @Override
    public final Temperature getTemperature() {
        return this.temperature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Food> getNearbyFoods(final Organism organism) {
        return this.getFoods(organism);
    }

    private Set<Food> getFoods(final Organism organism) {
        final Set<Food> ret = new HashSet<>();
        final Position p = this.getOrganismsMap().get(organism);
        final int radius = organism.getTraits().get(TraitType.FOODRADAR).getValue() - 1;
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                final Food f = this.getFoodsMap().get(new PositionImpl(p.getX() + i, p.getY() + j));
                if (f != null) {
                    ret.add(f);
                }
            }
        }
        return ret;
    }

}
