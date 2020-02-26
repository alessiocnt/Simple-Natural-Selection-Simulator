package view.entities;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple class that mantain the information for 
 * setting up the environment.
 *
 */
public class EnvironmentHolder {
    private int entityDimension;
    private int entitySpeed;
    private int entityQuantity;
    private int foodQuantity;
    private int foodVariation;

    /**
     * @return the initial dimention of entities.
     */
    public int getEntityDimension() {
        return entityDimension;
    }

    /**
     * @param entityDimension
     * set the initial dimention of entities.
     */
    public void setEntityDimension(final int entityDimension) {
        this.entityDimension = entityDimension;
    }

    /**
     * @return the initial speed of entities
     */
    public double getEntitySpeed() {
        return entitySpeed;
    }

    /**
     * @param entitySpeed
     * set the initial speed of entities
     */
    public void setEntitySpeed(final int entitySpeed) {
        this.entitySpeed = entitySpeed;
    }

    /**
     * @return the initial number of entities
     */
    public int getEntityQuantity() {
        return entityQuantity;
    }

    /**
     * @param entityQuantity
     * set the initial number of entities
     */
    public void setEntityQuantity(final int entityQuantity) {
        this.entityQuantity = entityQuantity;
    }

    /**
     * @return the food quantity available every day
     * it can be modified everyday by foodVariation
     */
    public int getFoodQuantity() {
        return foodQuantity;
    }

    /**
     * @param foodQuantity
     * set the food quantity available every day
     */
    public void setFoodQuantity(final int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    /**
     * @return the food variation
     */
    public int getFoodVariation() {
        return foodVariation;
    }

    /**
     * @param foodVariation
     * set the food variation
     */
    public void setFoodVariation(final int foodVariation) {
        this.foodVariation = foodVariation;
    }

    @Override
    public final String toString() {
        return List.of(this.entityDimension, this.entitySpeed, this.entityQuantity, this.foodQuantity, this.foodVariation)
                .stream()
                .map((x) -> x.toString())
                .collect(Collectors.joining(",", "[", "]"));
    }
}
