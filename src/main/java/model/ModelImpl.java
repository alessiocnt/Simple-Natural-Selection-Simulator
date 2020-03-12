package model;

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
import model.environment.EnvironmentFactoryImpl;
import model.environment.OrganismEnvironmentHolder;
import model.environment.position.Position;
import model.environment.temperature.TemperatureImpl;
import model.mutation.ChildrenQuantity;
import model.mutation.Dimension;
import model.mutation.FoodRadar;
import model.mutation.Speed;
import model.mutation.TemperatureSensibility;
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
    private FoodBuilder foodBuilder = new FoodBuilderImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public AdvancedEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodBuilder getFoodBuilder() {
        return this.foodBuilder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionController getActionController() {
        return actionController;
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

    private OrganismEnvironmentHolder getOrganismEnvironmentHolder() {
        return this.environment;
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
        organismBuilder.setTrait(TraitType.SPEED, new Speed(entitySpeed));
        organismBuilder.setTrait(TraitType.DIMENSION, new Dimension(entityDimension));
        organismBuilder.setTrait(TraitType.CHILDRENQUANTITY, new ChildrenQuantity(SetupValues.CHILDRENQUANTITY.getDefault()));
        organismBuilder.setTrait(TraitType.FOODRADAR, new FoodRadar(SetupValues.FOODRADAR.getDefault()));
        organismBuilder.setTrait(TraitType.TEMPERATURESENSIBILITY, new TemperatureSensibility());
        for (int i = 0; i < this.environment.getMorningFoodQuantity(); i++) {
            this.environment.addFood(foodBuilder.build());
        }
        for (int i = 0; i < entityQuantity; i++) {
            this.environment.addOrganism(organismBuilder.setEnvironmentKnowledge(this.getOrganismEnvironmentHolder()).build());
        }
    }
}
