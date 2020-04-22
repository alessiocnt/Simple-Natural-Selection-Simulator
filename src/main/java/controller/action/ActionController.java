/**
 * 
 */
package controller.action;

import java.util.Map;

import model.environment.daycicle.DayPeriod;

/**
 * Interface that models a controller for the Action.
 *
 */
public interface ActionController {

    /**
     * @return an EnumMap containing all the Actions
     */
    Map<DayPeriod, Action> getActions();
}
