/**
 * 
 */
package model.entity.food;

import model.entity.Energy;
import model.entity.EnergyImpl;

/**
 * Class that models a food's builder.
 *
 */
public class FoodBuilderImpl implements FoodBuilder {

    private static final int DEFAULTENERGY = 100;
    private static final Energy ENERGYVALUE = new EnergyImpl(FoodBuilderImpl.DEFAULTENERGY);
    private Energy energy;

    /**
     * Creates a new foodBuilder.
     */
    public FoodBuilderImpl() {
        this.energy = ENERGYVALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodBuilder setEnergy(final Energy energy) {
        this.energy = energy;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Food build() {
        return new Food(this.energy);
    }
}
