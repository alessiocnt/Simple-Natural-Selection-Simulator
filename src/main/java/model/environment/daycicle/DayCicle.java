package model.environment.daycicle;

/**
 * Represents the day and night controller.
 *
 */
public interface DayCicle {

    /**
     * Tells the day and night controller that a tick has passed in the simulation.
     */
    void nextTick();

    /**
     * @return the current day moment
     */
    DayPeriod getCurrentDayMoment();
}
