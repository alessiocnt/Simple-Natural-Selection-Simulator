/**
 * 
 */
package controller.action;

import java.util.EnumMap;

import model.environment.daycicle.DayPeriod;

/**
 * Interface that models a controller for the Action.
 *
 */
public interface ActionController {

    /**
     * @return an EnumMap containing all the Actions
     */
    EnumMap<DayPeriod, Action> getActions();
}
