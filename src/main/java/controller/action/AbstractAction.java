/**
 * 
 */
package controller.action;

import model.environment.daycicle.DayPeriod;

/**
 * Abstract class for Action.
 *
 */
public abstract class AbstractAction implements Action {

    /**
     * @param type
     *          the action's type
     */
    public AbstractAction(final DayPeriod type) {
        this.type = type;
    }

    private final DayPeriod type;

    /**
     * {@inheritDoc}
     */
    public DayPeriod getType() {
        return this.type;
    }

}
