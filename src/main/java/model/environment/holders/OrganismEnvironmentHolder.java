package model.environment.holders;

import model.environment.temperature.Temperature;

/**
 * An environment interface created for the organism.
 */
public interface OrganismEnvironmentHolder {

    /**
     * @return the current environment temperature
     */
    Temperature getTemperature();
}
