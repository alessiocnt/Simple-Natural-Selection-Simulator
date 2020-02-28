/**
 * 
 */
package controller.action;

import controller.action.strategy.EatLogics;
import controller.action.strategy.EatLogicsImpl;
import controller.action.strategy.MoveLogics;
import controller.action.strategy.MoveLogicsImpl;
import model.entity.Energy;
import model.entity.organism.Organism;
import model.environment.Environment;
import model.environment.daycicle.DayPeriod;
import utilities.Pair;

/**
 * This class performs all the actions that occur in a day-time.
 *
 */
public class DayAction extends AbstractAction {

    private final EatLogics eatLogic;
    private final MoveLogics moveLogic;
    private final Environment environment;

    /**
     * @param environment
     *          the environment of the system
     */
    public DayAction(final Environment environment) {
        super(DayPeriod.DAY);
        this.environment = environment;
        this.eatLogic = new EatLogicsImpl();
        this.moveLogic = new MoveLogicsImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(final Organism organism) {
        if (!tryToRemoveOrganism(organism)) {
            try {
                Pair<Integer, Integer> movementVectorPair = moveLogic.calculateVectorDirection(organism);
                environment.moveOrganism(organism, movementVectorPair.getX(), movementVectorPair.getY());
            } catch (Exception OutOfEnviromentException) {
                System.out.println("Out of environment, can't move.");
            }
            if (eatLogic.canEat(organism, environment.getFood(organism))) {
                eatLogic.eat(organism, environment.getFood(organism).get());
                environment.removeFood(environment.getFood(organism).get());
            }
        }
    }

    /**
     * @param organism the Organism
     * @return True if the Energy is enough for movement
     *         False instead.
     */
    private boolean isEnergyEnoughtToMove(final Organism organism) {
        return Energy.greater(organism.getEnergy(), moveLogic.computeConsumptionForMovement(organism))
                || organism.getEnergy().equals(moveLogic.computeConsumptionForMovement(organism));
    }

    /**
     * @param organism the Organism to try to remove
     * @return True if the Organism is removed.
     *         False instead.
     */
    private boolean tryToRemoveOrganism(final Organism organism) {
        if (!isEnergyEnoughtToMove(organism)) {
            environment.removeOrganism(organism);
            return true;
        }
        return false;
    }
}
