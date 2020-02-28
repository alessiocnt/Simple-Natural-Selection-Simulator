package model.entity;


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
     * {@inheritDoc}
     */
    @Override
    public Energy getEnergy() {
        return energy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnergy(final Energy energy) {
        this.energy = energy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Entity= [energy=" + energy + "]";
    }

}
