package model.entity.food;

import model.entity.Energy;
import model.entity.AbstractEntity;

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
     * @throws UnsupportedOperationException
     */
    @Override
    public final void setEnergy(final Energy energy) throws UnsupportedOperationException {
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
