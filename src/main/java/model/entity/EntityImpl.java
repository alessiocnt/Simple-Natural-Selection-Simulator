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
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * @return the energy
     */
    public Energy getEnergy() {
        return energy;
    }

    /**
     * @param energy the energy to set
     */
    public void setEnergy(final Energy energy) {
        this.energy = energy;
    }

    /**
     * toString for an Entity.
     */
    @Override
    public String toString() {
        return "Entity= [position=" + position + ", energy=" + energy + "]";
    }

}
