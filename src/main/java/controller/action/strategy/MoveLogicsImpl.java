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
import model.mutation.TraitType;
import utilities.Pair;

/**
 * Class that defines the logic for a movement due to the Organism's traits.
 *
 */
public class MoveLogicsImpl implements MoveLogics {

    /*
     * List representing the possible directions where an Organism can move.
     */
    private final List<Direction> directions;
    private Pair<Integer, Integer> directionVector;
    private static final Pair<Integer, Integer> ZERODIRECTIONVECTOR = new Pair<>(0, 0);

    /**
     * Constructor.
     */
    public MoveLogicsImpl() {
        this.directions = Arrays.asList(Direction.values());
        this.directionVector = ZERODIRECTIONVECTOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> calculateVectorDirection(final Organism organism) {
        Direction currentDirection;
        for (int i = 0; i < organism.getTraits().get(TraitType.SPEED).getValue(); i++) {
            currentDirection = this.getRandomDirection();
            this.directionVector = new Pair<>(directionVector.getX() + currentDirection.getXVariation(),
                                              directionVector.getY() + currentDirection.getYVariation());
        }
        detractConsumptionForMovement(organism, computeConsumptionForMovement(organism));
        return this.directionVector;
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
    public Pair<Integer, Integer> getZeroDirectionVector() {
        return MoveLogicsImpl.ZERODIRECTIONVECTOR;
    }

    /**
     * @return a random Direction
     */
    private Direction getRandomDirection() {
        Collections.shuffle(this.directions);
        return this.directions.get(0);
    }

    /**
     * @param organism the Organism that will perform the movement
     * @param energyToDetract the energy value that will be spent to perform the movement
     */
    private void detractConsumptionForMovement(final Organism organism, final Energy energyToDetract) {
        organism.getEnergy().detractEnergy(energyToDetract);
    }

}
