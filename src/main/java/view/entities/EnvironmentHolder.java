package view.entities;

import java.util.List;
import java.util.stream.Collectors;

import settings.SetupValues;

/**
 * Simple class that maintain the information for 
 * setting up the environment.
 *
 */
public class EnvironmentHolder {
    private int entityDimension;
    private int entitySpeed;
    private int entityQuantity;
    private int foodQuantity;
    private int foodVariation;
    private int temperature;

    /**
     * @return the initial dimension of entities.
     */
    public int getEntityDimension() {
        return this.entityDimension;
    }

    private void check(final SetupValues type, final int value) {
        if (!(value >= type.getStart() 
                && value <= type.getStop())) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param entityDimension
     * set the initial dimension of entities.
     */
    public void setEntityDimension(final int entityDimension) {
        this.check(SetupValues.DIMENSION, entityDimension);
        this.entityDimension = entityDimension;
    }

    /**
     * @return the initial speed of entities
     */
    public int getEntitySpeed() {
        return this.entitySpeed;
    }

    /**
     * @param entitySpeed
     * set the initial speed of entities
     */
    public void setEntitySpeed(final int entitySpeed) {
        this.check(SetupValues.SPEED, entitySpeed);
        this.entitySpeed = entitySpeed;
    }

    /**
     * @return the initial number of entities
     */
    public int getEntityQuantity() {
        return this.entityQuantity;
    }

    /**
     * @param entityQuantity
     * set the initial number of entities
     */
    public void setEntityQuantity(final int entityQuantity) {
        this.check(SetupValues.INITIALQUANTITY, entityQuantity);
        this.entityQuantity = entityQuantity;
    }

    /**
     * @return the food quantity available every day
     * it can be modified everyday by foodVariation
     */
    public int getFoodQuantity() {
        return this.foodQuantity;
    }

    /**
     * @param foodQuantity
     * set the food quantity available every day
     */
    public void setFoodQuantity(final int foodQuantity) {
        this.check(SetupValues.FOODQUANTITY, foodQuantity);
        this.foodQuantity = foodQuantity;
    }

    /**
     * @return the food variation
     */
    public int getFoodVariation() {
        return this.foodVariation;
    }

    /**
     * @param foodVariation
     * set the food variation
     */
    public void setFoodVariation(final int foodVariation) {
        this.check(SetupValues.FOODVARIATION, foodVariation);
        this.foodVariation = foodVariation;
    }

    /**
     * @return the environment temperature.
     */
    public int getTemperature() {
        return this.temperature;
    }

    /**
     * @param temperature
     * set the temperature of environment.
     */
    public void setTemperature(final int temperature) {
        this.check(SetupValues.TEMPERATURE, temperature);
        this.temperature = temperature;
    }

    @Override
    public final String toString() {
        return List.of(this.entityDimension, this.entitySpeed, this.entityQuantity, this.foodQuantity, this.foodVariation, this.temperature)
                .stream()
                .map((x) -> x.toString())
                .collect(Collectors.joining(",", "[", "]"));
    }
}
