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

    private static final Energy ENERGYVALUE = new EnergyImpl(50);
    private Energy energy;

    /**
     * @param energy
     */
    public FoodBuilderImpl() {
        this.energy = ENERGYVALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Food build() {
        return new Food(this.energy);
    }

}
