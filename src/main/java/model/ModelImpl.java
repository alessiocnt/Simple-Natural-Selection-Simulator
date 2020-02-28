package model;

import model.environment.daycicle.DayCicle;

/**
 * Model implementation.
 *
 */
public class ModelImpl implements Model {

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final DayCicle dayCicle) {
        // TODO Auto-generated method stub
        dayCicle.nextTick();
        /*Iterator<Organism> organisms = this.environment.getOrganisms();
        while (organisms.hasNext()) {
            this.actionController.getActions().get(dayCicle.getCurrentDayMoment()).perform(organisms.next());
        }*/
    }
}
