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
     * @return the energy value.
     */
    public int getEnergy() {
        return energyLevel;
    }

    /**
     * @param energy
     *         the energy value to set.
     */
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
