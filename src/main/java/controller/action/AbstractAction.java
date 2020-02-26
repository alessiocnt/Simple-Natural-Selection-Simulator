/**
 * 
 */
package controller.action;

/**
 * Abstract class for Action.
 *
 */
public abstract class AbstractAction implements Action {

    /**
     * @param type
     *          the action's type
     */
    public AbstractAction(final ActionType type) {
        this.type = type;
    }

    private final ActionType type;

    /**
     * {@inheritDoc}
     */
    public ActionType getType() {
        return this.type;
    }

}
