package model;

import java.util.Iterator;
import java.util.Set;

import controller.action.ActionController;
import controller.action.ActionControllerImpl;
import model.entity.food.Food;
import model.entity.food.FoodBuilder;
import model.entity.food.FoodBuilderImpl;
import model.entity.organism.Organism;
import model.entity.organism.OrganismBuilder;
import model.entity.organism.OrganismBuilderImpl;
import model.environment.AdvancedEnvironment;
import model.environment.BasicEnvironment;
import model.environment.EnvironmentFactoryImpl;
import model.environment.daycicle.DayCicle;
import model.environment.daycicle.DayPeriod;
import model.environment.position.Position;
import model.environment.temperature.TemperatureImpl;
import model.mutation.ChildrenQuantity;
import model.mutation.Dimension;
import model.mutation.Speed;
import model.mutation.TraitType;
import model.utilities.DimensionConverter;
import settings.SetupValues;
import utilities.Pair;
import view.entities.EnvironmentHolder;

/**
 * Model implementation.
 *
 */
public class ModelImpl implements Model {

    private AdvancedEnvironment environment;
    private ActionController actionController;

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final DayCicle dayCicle) {
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
        return this.environment.getCurrendOrganismQuantity() == 0;
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
        final int width = 200;
        final int height = 100;
        this.environment = new EnvironmentFactoryImpl()
                .createAdvancedEnviroment(width, height, holder.getFoodQuantity(), holder.getFoodVariation(), new TemperatureImpl(holder.getTemperature()));
        initEnvironment(holder.getEntityQuantity(), holder.getEntitySpeed(), holder.getEntityDimension());
        this.actionController = new ActionControllerImpl(this.environment);
    }

    private void initEnvironment(final int entityQuantity, final int entitySpeed, final int entityDimension) {
        FoodBuilder foodBuilder = new FoodBuilderImpl();
        OrganismBuilder organismBuilder = new OrganismBuilderImpl(DimensionConverter.toEnergy(entityDimension));
        organismBuilder.trait(TraitType.SPEED, new Speed(entitySpeed));
        organismBuilder.trait(TraitType.DIMENSION, new Dimension(entityDimension));
        organismBuilder.trait(TraitType.CHILDRENQUANTITY, new ChildrenQuantity(SetupValues.CHILDRENQUANTITY.getDefault()));
        for (int i = 0; i < this.environment.getMorningFoodQuantity(); i++) {
            this.environment.addFood(foodBuilder.build());
        }
        for (int i = 0; i < entityQuantity; i++) {
            this.environment.addOrganism(organismBuilder.build());
        }
    }
}
