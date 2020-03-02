package model.environment.daycicle;

public class DayCicleImpl implements DayCicle {

    private int elapsedTicks = 0;
    private final int dayDuration;

    /**
     * Creates a new DayCicleImpl.
     * @param dayDuration
     *      the duration in ticks of a day 
     */
    public DayCicleImpl(final int dayDuration) {
        this.dayDuration = dayDuration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextTick() {
        elapsedTicks++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DayPeriod getCurrentDayMoment() {
        // the last tick is the night
        return elapsedTicks % dayDuration != dayDuration - 1 ? DayPeriod.DAY : DayPeriod.NIGHT;
    }

}
