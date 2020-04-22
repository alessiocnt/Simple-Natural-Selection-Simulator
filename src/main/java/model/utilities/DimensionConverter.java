package model.utilities;

import model.entity.Energy;
import model.entity.EnergyImpl;

/**
 * Utility for converting Dimension in other format.
 */
public final class DimensionConverter {
    private static final int DIMTOENERGY = 5;

    private DimensionConverter() {
    }

    /**
     * Convert Dimension to Energy. 
     * @param organismDimension 
     * dimension to convert.
     * @return energy object.
     */
    public static Energy toEnergy(final int organismDimension) {
        return new EnergyImpl(organismDimension * DimensionConverter.DIMTOENERGY);
    }
}
