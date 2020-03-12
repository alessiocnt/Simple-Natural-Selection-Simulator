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
    public void setEnergy(final Energy energy) {
        this.energyLevel = energy.getEnergy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEnergy(final Energy energy) {
        this.energyLevel += energy.getEnergy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detractEnergy(final Energy energy) {
        this.energyLevel -= energy.getEnergy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + energyLevel;
        return result;
    }

    /**
     * @param obj
     *      the object to compare
     * @return
     *      true due to their energy level
     *      false instead
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) { 
            return true; 
        }
        if (!(obj instanceof EnergyImpl)) { 
            return false; 
        } 
        final EnergyImpl c = (EnergyImpl) obj; 
        return c.getEnergy() == this.getEnergy();
    } 

    /**
     * toString for an Energy level.
     */
    @Override
    public String toString() {
        return String.valueOf(energyLevel);
    }

}
