/**
 * 
 */
package controller.action;

import java.util.EnumMap;
import model.environment.Environment;
import model.environment.daycicle.DayPeriod;

/**
 * Class that defines a controller for the Action.
 *
 */
public class ActionControllerImpl implements ActionController {

    private final EnumMap<DayPeriod, Action> actions;

    /**
     * @param environment the simulation Environment
     */
    public ActionControllerImpl(final Environment environment) {
        this.actions = new EnumMap<>(DayPeriod.class);
        this.actions.put(DayPeriod.DAY, new DayAction(environment));
        this.actions.put(DayPeriod.NIGHT, new NightAction(environment));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumMap<DayPeriod, Action> getActions() {
        return this.actions;
    }

}