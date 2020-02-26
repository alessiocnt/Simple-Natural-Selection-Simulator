/**
 * 
 */
package model.entity;

/**
 * Class implementation of energy.
 *
 */
public class EnergyImpl implements Energy {

    private int energyLevel;

    /**
     * @param energyLevel
     *          the energy value to set.
     */
    public EnergyImpl(final int energyLevel) {
        this.energyLevel = energyLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEnergy() {
        return energyLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnergy(final int energy) {
        this.energyLevel = energy;
    }

    /**
     * toString for an Energy level.
     */
    @Override
    public String toString() {
        return "" + energyLevel;
    }

}
