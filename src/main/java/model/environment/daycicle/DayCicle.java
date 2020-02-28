package model.environment.daycicle;

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
