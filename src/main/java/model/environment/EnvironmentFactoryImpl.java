package model.environment;

import model.environment.temperature.Temperature;

/**
 * Basic environment factory.
 */
public class EnvironmentFactoryImpl implements EnvironmentFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public BasicEnvironment createBasicEnviroment(final int xDimension, final int yDimension, final int morningFoodQuantity, final int dailyFoodQuantityModification) {
        return new BasicEnvironmentImpl(xDimension, yDimension, morningFoodQuantity, dailyFoodQuantityModification);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdvancedEnvironment createAdvancedEnviroment(final int xDimension, final int yDimension, final int morningFoodQuantity,
            final int dailyFoodQuantityModification, final Temperature temperature) {
        return new AdvancedEnvironmentImpl(xDimension, yDimension, morningFoodQuantity, dailyFoodQuantityModification, temperature);
    }

}
