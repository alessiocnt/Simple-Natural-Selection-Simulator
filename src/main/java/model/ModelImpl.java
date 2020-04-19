package model;

import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;

import controller.action.ActionController;
import controller.action.ActionControllerImpl;
import model.entity.food.Food;
import model.entity.food.FoodBuilder;
import model.entity.food.FoodBuilderImpl;
import model.entity.organism.Organism;
import model.entity.organism.OrganismBuilder;
import model.entity.organism.OrganismBuilderImpl;
import model.environment.AdvancedEnvironment;
import model.environment.factory.EnvironmentFactoryImpl;
import model.environment.holders.OrganismEnvironmentHolder;
import model.environment.position.Position;
import model.environment.temperature.TemperatureImpl;
import model.mutation.TraitType;
import model.mutation.trait.ChildrenQuantity;
import model.mutation.trait.Dimension;
import model.mutation.trait.FoodRadar;
import model.mutation.trait.Speed;
import model.mutation.trait.TemperatureSensibility;
import model.utilities.DimensionConverter;
import settings.SetupValues;
import view.entities.EnvironmentHolder;

/**
 * Model implementation.
 *
 */
public class ModelImpl implements Model {

    private AdvancedEnvironment environment;
    private ActionController actionController;
    private final FoodBuilder foodBuilder = new FoodBuilderImpl();

    @Override
    public final AdvancedEnvironment getEnvironment() {
        return this.environment;
    }

    @Override
    public final FoodBuilder getFoodBuilder() {
        return this.foodBuilder;
    }

    @Override
    public final ActionController getActionController() {
        return actionController;
    }

    @Override
    public final boolean isSimulationOver() {
        return this.environment.getCurrendOrganismQuantity() == 0;
    }

    @Override
    public final Set<ImmutablePair<Position, Food>> getFoods() {
        return this.environment.getPositionFoods();
    }

    @Override
    public final Set<ImmutablePair<Position, Organism>> getOrganisms() {
        return this.environment.getPositionOrganisms();
    }

    @Override
    public final Position getEnvironmentDimension() {
        return this.environment.getDimension();
    }

    private OrganismEnvironmentHolder getOrganismEnvironmentHolder() {
        return this.environment;
    }

    @Override
    public final void prepareEnvironment(final EnvironmentHolder holder) {
        final int width = 200;
        final int height = 100;
        this.environment = new EnvironmentFactoryImpl()
                .createAdvancedEnviroment(width, height, holder.getFoodQuantity(), holder.getFoodVariation(), new TemperatureImpl(holder.getTemperature()));
        initEnvironment(holder.getEntityQuantity(), holder.getEntitySpeed(), holder.getEntityDimension());
        this.actionController = new ActionControllerImpl(this.environment);
    }

    private void initEnvironment(final int entityQuantity, final int entitySpeed, final int entityDimension) {
        final OrganismBuilder organismBuilder = new OrganismBuilderImpl(DimensionConverter.toEnergy(entityDimension));
        organismBuilder.setTrait(TraitType.SPEED, new Speed(entitySpeed));
        organismBuilder.setTrait(TraitType.DIMENSION, new Dimension(entityDimension));
        organismBuilder.setTrait(TraitType.CHILDRENQUANTITY, new ChildrenQuantity(SetupValues.CHILDRENQUANTITY.getDefault()));
        organismBuilder.setTrait(TraitType.FOODRADAR, new FoodRadar(SetupValues.FOODRADAR.getDefault()));
        organismBuilder.setTrait(TraitType.TEMPERATURESENSIBILITY, new TemperatureSensibility());
        for (int i = 0; i < this.environment.getMorningFoodQuantity(); i++) {
            this.environment.addFood(this.foodBuilder.build());
        }
        for (int i = 0; i < entityQuantity; i++) {
            this.environment.addOrganism(organismBuilder.setEnvironmentKnowledge(this.getOrganismEnvironmentHolder()).build());
        }
    }
}
