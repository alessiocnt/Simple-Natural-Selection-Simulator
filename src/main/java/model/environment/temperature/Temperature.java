package model.environment.temperature;
/**
 * An interface that models a Temperature.
 */
public interface Temperature {

    /**
     * Sets the temperature value.
     * @param value
     *      the temperature value
     */
    void setValue(double value);

    /**
     * @return the temperature value
     */
    double getValue();
}
