package model;

import java.util.Iterator;

import controller.action.ActionController;
import model.entity.food.FoodBuilder;
import model.entity.organism.Organism;
import model.environment.BasicEnvironment;
import model.environment.daycicle.DayCicle;
import model.environment.daycicle.DayPeriod;

/**
 * Model implementation.
 *
 */
public class ModelImpl implements Model {

    private BasicEnvironment environment;
    private ActionController actionController;
    private FoodBuilder foodBuilder;
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final DayCicle dayCicle) {
        // TODO Auto-generated method stub
        dayCicle.nextTick();
        // Updates the environment food
        updateEnvironmentFood(dayCicle.getCurrentDayMoment());
        Iterator<Organism> organisms = this.environment.getOrganisms();
        while (organisms.hasNext()) {
            this.actionController.getActions().get(dayCicle.getCurrentDayMoment()).perform(organisms.next());
        }
    }

    private void updateEnvironmentFood(final DayPeriod currentDayMoment) {
        // if it's night refills the environment with food for the next day
        if (currentDayMoment == DayPeriod.NIGHT) {
            //tells the environment a new day is coming
            this.environment.nextDay();
            for (int i = 0; i < this.environment.getMorningFoodQuantity(); i++) {
                this.environment.addFood(this.foodBuilder.build());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSimulationOver() {
        return this.environment.getCurrendOrganismQuantity() == 0;
    }
}
