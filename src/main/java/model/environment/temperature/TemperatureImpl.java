package model.environment.temperature;

/**
 * Temperature implementation.
 *
 */
public class TemperatureImpl implements Temperature {

    private double value;

    /**
     * @param value
     * the temperature value.
     */
    public TemperatureImpl(final double value) {
        this.value = value;
    }

    @Override
    public final void setValue(final double value) {
        this.value = value;
    }

    @Override
    public final double getValue() {
        // TODO Auto-generated method stub
        return this.value;
    }

}
