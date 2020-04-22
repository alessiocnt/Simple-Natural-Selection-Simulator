package model.entity.food;

import model.entity.Energy;
import model.entity.AbstractEntity;

/**
 * Implementation of Food.
 * It's the food available for the organisms.
 *
 */
public class Food extends AbstractEntity {

    private static final String EXCEPTIONMESSAGE = "Operation not supported for type Food.";
    /**
     * @param energy
     *          The current Food energy
     */
    protected Food(final Energy energy) {
        super(energy);
    }

    /**
     * This method in Unsupported for Object Food.
     * @param energy
     *          The Food energy
     */
    @Override
    public final void setEnergy(final Energy energy) {
        throw new UnsupportedOperationException(EXCEPTIONMESSAGE);
    }

    /**
     * toString for a Food.
     */
    @Override
    public String toString() {
        return "Food= [energy=" + this.getEnergy() + "]";
    }

}
