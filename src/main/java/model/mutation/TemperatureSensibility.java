package model.mutation;

import model.entity.organism.Organism;
import model.mutation.foodconsumption.strategy.FoodConsumptionFunc;

/**
 * Temperature sensibility.
 */
public class TemperatureSensibility extends AbstractTrait {

    /**
     * Constructor.
     */
    public TemperatureSensibility() {
        super(0, new Sensibility(), TraitType.TEMPERATURESENSIBILITY);
    }

    /*
     * It's private static because it's limited to this class and I want to make it usable only within this class.
     */
    private static class Sensibility implements FoodConsumptionFunc {
        private static final int NUMERATOR = 200;
        private static final double PARAMETER = 2;

        @Override
        public final int getConsumption(final Organism organism) {
            final double environmentTemp = organism.getEnvironmentKnowledge().getTemperature().getValue();
            final int organismDim = organism.getTraits().get(TraitType.DIMENSION).getValue();
            return (int) (Math.pow((((double) 1 / 4) * environmentTemp - Sensibility.PARAMETER - ((double) Sensibility.NUMERATOR / organismDim)), 2));
        }
    }

    //Temperature sensibility hasn't a value.
    @Override
    public final int getValue() {
        throw new UnsupportedOperationException();
    }
}
