package model.entity.food;

import model.entity.Energy;
import model.entity.EntityImpl;
import model.environment.position.Position;

public class Food extends EntityImpl {

    private static final String EXCEPTIONMESSAGE = "Operation not supported for type Food.";
    /**
     * @param position
     *          The current Food position
     * @param energy
     *          The current Food energy
     */
    public Food(final Energy energy) {
        super(energy);
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
        return "Food= [energy=" + this.getEnergy() + "]";
    }

}
