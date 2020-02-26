package model.entity;

public class EntityImpl implements Entity {

    private Position position;
    private Energy energy;

    /**
     * @param position
     *          The current Entity position
     * @param energy
     *          The current Entity energy
     */
    public EntityImpl(final Position position, final Energy energy) {
        this.position = position;
        this.energy = energy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Position position) {
        this.position = position;
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
        return "Entity= [position=" + position + ", energy=" + energy + "]";
    }

}
