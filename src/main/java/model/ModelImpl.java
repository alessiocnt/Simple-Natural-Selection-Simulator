package model;

import java.util.Iterator;
import java.util.Set;

import controller.action.ActionController;
import controller.action.ActionControllerImpl;
import model.entity.EnergyImpl;
import model.entity.food.Food;
import model.entity.food.FoodBuilder;
import model.entity.food.FoodBuilderImpl;
import model.entity.organism.Organism;
import model.entity.organism.OrganismBuilder;
import model.entity.organism.OrganismBuilderImpl;
import model.environment.BasicEnvironment;
import model.environment.BasicEnvironmentFactoryImpl;
import model.environment.daycicle.DayCicle;
import model.environment.daycicle.DayPeriod;
import model.environment.position.Position;
import model.mutation.ChildrenQuantity;
import model.mutation.Dimension;
import model.mutation.Speed;
import model.mutation.TraitType;
import utilities.Pair;
import view.entities.EnvironmentHolder;

/**
 * Model implementation.
 *
 */
public class ModelImpl implements Model {

    private BasicEnvironment environment;
    private ActionController actionController;

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
        FoodBuilder foodBuilder = new FoodBuilderImpl();
        // if it's night refills the environment with food for the next day
        if (currentDayMoment == DayPeriod.NIGHT) {
            //tells the environment a new day is coming
            this.environment.nextDay();
            for (int i = 0; i < this.environment.getMorningFoodQuantity(); i++) {
                this.environment.addFood(foodBuilder.build());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSimulationOver() {
        //return this.environment.getCurrendOrganismQuantity() == 0;
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Pair<Position, Food>> getFoods() {
        return this.environment.getPositionFoods();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Pair<Position, Organism>> getOrganisms() {
        return this.environment.getPositionOrganisms();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getEnvironmentDimension() {
        return this.environment.getDimension();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void prepareEnvironment(final EnvironmentHolder holder) {
        this.environment = new BasicEnvironmentFactoryImpl()
                .createBasicEnviroment(100, 100, holder.getFoodQuantity(), holder.getFoodVariation());
        initEnvironment(holder.getEntityQuantity(), holder.getEntitySpeed(), holder.getEntityDimension());
        this.actionController = new ActionControllerImpl(this.environment);
    }

    private void initEnvironment(final int entityQuantity, final double entitySpeed, final int entityDimension) {
        FoodBuilder foodBuilder = new FoodBuilderImpl();
        // TODO energia per l'organismo???
        OrganismBuilder organismBuilder = new OrganismBuilderImpl(new EnergyImpl(100000));
        // TODO speed e' int o double???
        organismBuilder.trait(TraitType.SPEED, new Speed((int) entitySpeed));
        organismBuilder.trait(TraitType.DIMENSION, new Dimension(entityDimension));
        // TODO quantita' di figli iniziale???
        organismBuilder.trait(TraitType.CHILDRENQUANTITY, new ChildrenQuantity(1));
        for (int i = 0; i < this.environment.getMorningFoodQuantity(); i++) {
            this.environment.addFood(foodBuilder.build());
        }
        for (int i = 0; i < entityQuantity; i++) {
            this.environment.addOrganism(organismBuilder.build());
        }
    }
}
