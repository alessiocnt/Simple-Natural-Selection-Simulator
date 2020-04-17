/**
 * 
 */
package controller.action;

import java.util.Set;

import controller.action.strategy.Direction;
import controller.action.strategy.EatLogics;
import controller.action.strategy.EatLogicsImpl;
import controller.action.strategy.MoveLogics;
import controller.action.strategy.MoveLogicsImpl;
import model.entity.Energy;
import model.entity.food.Food;
import model.entity.organism.Organism;
import model.environment.AdvancedEnvironment;
import model.environment.daycicle.DayPeriod;
import model.environment.exceptions.OutOfEnviromentException;
import model.mutation.TraitType;

/**
 * This class performs all the actions that occur in a day-time.
 *
 */
public class DayAction extends AbstractAction {

    private final EatLogics eatLogic;
    private final MoveLogics moveLogic;
    private final AdvancedEnvironment environment;

    /**
     * @param environment
     *          the environment of the system
     */
    public DayAction(final AdvancedEnvironment environment) {
        super(DayPeriod.DAY);
        this.environment = environment;
        this.eatLogic = new EatLogicsImpl();
        this.moveLogic = new MoveLogicsImpl();
    }

    @Override
    public final void perform(final Organism organism) {
        if (!tryToRemoveOrganism(organism)) {
            try {
                Direction currentDirection;
                Set<Food> reachableFood;
                for (int i = 0; i < organism.getTraits().get(TraitType.SPEED).getValue(); i++) {
                    currentDirection = moveLogic.getRandomDirection();
                    environment.moveOrganism(organism, currentDirection.getXVariation(), currentDirection.getYVariation());
                    reachableFood = environment.getNearbyFoods(organism);
                    if (eatLogic.canEat(organism, reachableFood)) {
                        eatLogic.eat(organism, reachableFood);
                        reachableFood.forEach(f -> environment.removeFood(f));
                    }
                }
                moveLogic.detractConsumptionForMovement(organism);
            } catch (OutOfEnviromentException e) {
//          Purposely left blank. If an organism try to go outside the environment it will not move.
//                System.out.println(e.getMessage() + " " + organism);
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
