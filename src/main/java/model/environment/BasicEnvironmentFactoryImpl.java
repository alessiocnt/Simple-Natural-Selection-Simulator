package model.environment;

/**
 * Basic environment factory.
 */
public class BasicEnvironmentFactoryImpl implements BasicEnvironmentFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public BasicEnvironment createBasicEnviroment(final int xDimension, final int yDimension, final int morningFoodQuantity, final int dailyFoodQuantityModification) {
        return new BasicEnvironmentImpl(xDimension, yDimension, morningFoodQuantity, dailyFoodQuantityModification);
    }

}
