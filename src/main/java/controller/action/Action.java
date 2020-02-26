/**
 * 
 */
package controller.action;

import model.entity.organism.Organism;

/**
 * Interface that models an Action.
 *
 */
public interface Action {

    /**
     * @return the ActionType of the current action
     */
    ActionType getType();

    /**
     * @param organism the pivot on which the action is performed 
     */
    void perform(Organism organism);
}
