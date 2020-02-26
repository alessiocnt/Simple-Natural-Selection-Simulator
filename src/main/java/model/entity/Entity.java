/**
 * 
 */
package model.entity;

/**
 * Interface that defines an Entity which can populate the environment.
 *
 */
public interface Entity {

    /**
     * Represents the entity position.
     * 
     * @return the current position.
     */
    Position getPosition();

    /**
     * Set the entity position.
     * 
     * @param position 
     *          to set
     */
    void setPosition(Position position);

    /**
     * Represents the entity energy.
     * 
     * @return the current energy.
     */
    Energy getEnergy();

    /**
     * Set the entity energy.
     * 
     * @param energy 
     *          to set
     */
    void setEnergy(Energy energy);
}
