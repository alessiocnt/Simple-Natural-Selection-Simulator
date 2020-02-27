/**
 * 
 */
package controller.action;

import model.entity.organism.Organism;
import model.environment.DayPeriod;

/**
 * Interface that models an Action.
 *
 */
public interface Action {

    /**
     * @return the ActionType of the current action
     */
    DayPeriod getType();

    /**
     * @param organism the pivot on which the action is performed 
     */
    void perform(Organism organism);
}
