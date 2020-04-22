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

    @Override
    public final int getEnergy() {
        return energyLevel;
    }

    @Override
    public final void setEnergy(final Energy energy) {
        this.energyLevel = energy.getEnergy();
    }

    @Override
    public final void addEnergy(final Energy energy) {
        this.energyLevel += energy.getEnergy();
    }

    @Override
    public final void detractEnergy(final Energy energy) {
        this.energyLevel -= energy.getEnergy();
    }

    @Override
    public final int hashCode() {
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
    public final boolean equals(final Object obj) {
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
