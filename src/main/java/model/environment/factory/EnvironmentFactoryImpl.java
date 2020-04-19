package model.environment.factory;

import model.environment.AdvancedEnvironment;
import model.environment.AdvancedEnvironmentImpl;
import model.environment.BasicEnvironment;
import model.environment.BasicEnvironmentImpl;
import model.environment.temperature.Temperature;

/**
 * Basic environment factory.
 */
public class EnvironmentFactoryImpl implements EnvironmentFactory {

    @Override
    public final BasicEnvironment createBasicEnviroment(final int xDimension, final int yDimension, final int morningFoodQuantity, final int dailyFoodQuantityModification) {
        return new BasicEnvironmentImpl(xDimension, yDimension, morningFoodQuantity, dailyFoodQuantityModification);
    }

    @Override
    public final AdvancedEnvironment createAdvancedEnviroment(final int xDimension, final int yDimension, final int morningFoodQuantity,
            final int dailyFoodQuantityModification, final Temperature temperature) {
        return new AdvancedEnvironmentImpl(xDimension, yDimension, morningFoodQuantity, dailyFoodQuantityModification, temperature);
    }

}
