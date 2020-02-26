package model.entity.food;

import model.entity.Energy;
import model.entity.EntityImpl;
import model.entity.Position;

public class Food extends EntityImpl {

    private static final String EXCEPTIONMESSAGE = "Operation not supported for type Food.";
    /**
     * @param position
     *          The current Food position
     * @param energy
     *          The current Food energy
     */
    public Food(final Position position, final Energy energy) {
        super(position, energy);
    }

    /**
     * @param position
     *          The Food position
     * @throws UnsupportedOperationException
     */
    @Override
    public void setPosition(final Position position) {
        throw new UnsupportedOperationException(EXCEPTIONMESSAGE);
    }

    /**
     * @param energy
     *          The Food energy
     * @throws UnsupportedOperationException
     */
    @Override
    public void setEnergy(final Energy energy) {
        throw new UnsupportedOperationException(EXCEPTIONMESSAGE);
    }

    /**
     * toString for a Food.
     */
    @Override
    public String toString() {
        return "Food= [position=" + this.getPosition() +  ", energy=" + this.getEnergy() + "]";
    }

}
