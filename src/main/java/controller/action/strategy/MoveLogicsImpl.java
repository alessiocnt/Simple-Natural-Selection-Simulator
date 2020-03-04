/**
 * 
 */
package controller.action.strategy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.entity.Energy;
import model.entity.EnergyImpl;
import model.entity.organism.Organism;

/**
 * Class that defines the logic for a movement due to the Organism's traits.
 *
 */
public class MoveLogicsImpl implements MoveLogics {

    /*
     * List representing the possible directions where an Organism can move.
     */
    private final List<Direction> directions;

    /**
     * Constructor.
     */
    public MoveLogicsImpl() {
        this.directions = Arrays.asList(Direction.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Energy computeConsumptionForMovement(final Organism organism) {
        Energy consumpion = new EnergyImpl(0);
        organism.getTraits().keySet().stream()
                .filter(k -> k.affectMovement())
                .forEach(e -> consumpion.addEnergy(organism.getTraits().get(e).getFoodConsumption(organism)));
        return consumpion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction getRandomDirection() {
        Collections.shuffle(this.directions);
        return this.directions.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detractConsumptionForMovement(final Organism organism) {
        organism.getEnergy().detractEnergy(computeConsumptionForMovement(organism));
    }

}
