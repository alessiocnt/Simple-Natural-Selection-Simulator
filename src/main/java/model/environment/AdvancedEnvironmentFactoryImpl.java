package model.environment;

import model.environment.temperature.Temperature;

public class AdvancedEnvironmentFactoryImpl implements AdvancedEnvironmentFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public AdvancedEnvironment createBasicEnviroment(final int xDimension, final int yDimension, final int morningFoodQuantity,
            final int dailyFoodQuantityModification, final Temperature temperature) {
        return new AdvancedEnvironmentImpl(xDimension, yDimension, morningFoodQuantity, dailyFoodQuantityModification, temperature);
    }

}
