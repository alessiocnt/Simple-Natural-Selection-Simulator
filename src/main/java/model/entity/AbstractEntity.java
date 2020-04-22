package model.entity;


/**
 * Abstract class to describe Entities.
 */
public abstract class AbstractEntity implements Entity {

    private Energy energy;

    /**
     * @param energy
     *          The current Entity energy
     */
    public AbstractEntity(final Energy energy) {
        this.energy = energy;
    }

    /**
     * Represents the entity energy.
     * 
     * @return the current energy.
     */
    @Override
    public Energy getEnergy() {
        return energy;
    }

    /**
     * Set the entity energy.
     * 
     * @param energy 
     *          to set
     */
    @Override
    public void setEnergy(final Energy energy) {
        this.energy = energy;
    }

    /**
     * toString for an Entity.
     */
    @Override
    public String toString() {
        return "Entity= [energy=" + energy + "]";
    }

}
