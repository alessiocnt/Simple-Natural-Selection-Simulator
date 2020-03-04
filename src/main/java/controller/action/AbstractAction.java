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

    private final DayPeriod type;
    /**
     * @param type
     *          the action's type
     */
    public AbstractAction(final DayPeriod type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DayPeriod getType() {
        return this.type;
    }

}
