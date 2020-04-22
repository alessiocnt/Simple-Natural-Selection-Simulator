/**
 * 
 */
package controller.action.strategy;

import java.util.Set;

import model.entity.Energy;
import model.entity.food.Food;
import model.entity.organism.Organism;
import model.mutation.TraitType;
import model.mutation.trait.Trait;
import model.utilities.DimensionConverter;

/**
 * Class that defines the logic for eating a food.
 *
 */
public class EatLogicsImpl implements EatLogics {

    @Override
    public final void eat(final Organism organism, final Set<Food> foods) {
        foods.forEach(f -> organism.getEnergy().addEnergy(f.getEnergy()));
        this.adjustEnergyLevel(organism);
    }

    @Override
    public final boolean canEat(final Organism organism, final Set<Food> foods) {
        return !organism.getEnergy().equals(this.calculateOrganismMaxEnergy(organism.getTraits().get(TraitType.DIMENSION)))
                && !foods.isEmpty();
    }

    /*
     * Calculates Organism's maximum energy level from its size.
     */
    private Energy calculateOrganismMaxEnergy(final Trait organismDimension) {
        return DimensionConverter.toEnergy(organismDimension.getValue());
    }

    /*
     * Adjust Organism's energy level due to its maximum energy level.
     */
    private void adjustEnergyLevel(final Organism organism) {
        if (Energy.greater(organism.getEnergy(), this.calculateOrganismMaxEnergy(organism.getTraits().get(TraitType.DIMENSION)))) {
            organism.setEnergy(this.calculateOrganismMaxEnergy(organism.getTraits().get(TraitType.DIMENSION)));
        }
    }
}
