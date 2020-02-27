/**
 * 
 */
package controller.action.strategy;

import java.util.Optional;

import model.entity.Energy;
import model.entity.EnergyImpl;
import model.entity.food.Food;
import model.entity.organism.Organism;
import model.mutation.Trait;
import model.mutation.TraitType;

/**
 * Class that defines the logic for eating a food.
 *
 */
public class EatLogicsImpl implements EatLogics {

    /**
     * {@inheritDoc}
     */
    @Override
    public void eat(final Organism organism, final Food food) {
        organism.getEnergy().addEnergy(food.getEnergy());
        adjustEnergyLevel(organism);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canEat(final Organism organism, final Optional<Food> food) {
        return !organism.getEnergy().equals(calculeteOrganismMaxEnergy(organism.getTraits().get(TraitType.DIMENSION))) 
                && !food.isEmpty();
    }

    /*
     * This method calculates Organism's maximum energy level from its size.
     */
    private Energy calculeteOrganismMaxEnergy(final Trait organismDimension) {
        return new EnergyImpl(organismDimension.getValue());
    }

    /*
     * This method adjust Organism's energy level due to its maximum energy level.
     */
    private void adjustEnergyLevel(final Organism organism) {
        if (Energy.greater(organism.getEnergy(), calculeteOrganismMaxEnergy(organism.getTraits().get(TraitType.DIMENSION)))) {
            organism.setEnergy(calculeteOrganismMaxEnergy(organism.getTraits().get(TraitType.DIMENSION)));
        }
    }
}
