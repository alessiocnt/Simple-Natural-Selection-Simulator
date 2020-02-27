/**
 * 
 */
package controller.action;

import controller.action.strategy.EatLogics;
import controller.action.strategy.EatLogicsImpl;
import controller.action.strategy.MoveLogics;
import controller.action.strategy.MoveLogicsImpl;

import model.entity.organism.Organism;
import model.environment.DayPeriod;
import model.environment.Environment;

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

    @Override
    public void perform(final Organism organism) {
        // TODO Auto-generated method stub

    }

    private boolean isEnergyEnoughtToMove(final Organism organism) {
       //return organism.
        return true;
    }
}
