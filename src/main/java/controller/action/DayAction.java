/**
 * 
 */
package controller.action;

import controller.action.strategy.EatLogics;
import controller.action.strategy.EatLogicsImpl;
import controller.action.strategy.MoveLogics;
import controller.action.strategy.MoveLogicsImpl;
import model.entity.organism.Organism;
import model.enviroment.Enviroment;

/**
 * This class performs all the actions that occur in a day-time.
 *
 */
public class DayAction extends AbstractAction {

    private final EatLogics eatLogic;
    private final MoveLogics moveLogic;
    private final Enviroment environment;

    /**
     * @param environment
     *          the environment of the system
     */
    public DayAction(final Enviroment environment) {
        super(ActionType.DAYACTION);
        this.environment = environment;
        this.eatLogic = new EatLogicsImpl(this.environment.DIMENSIONE);
        this.moveLogic = new MoveLogicsImpl();
    }

    @Override
    public void perform(Organism organism) {
        // TODO Auto-generated method stub
        
    }

}
