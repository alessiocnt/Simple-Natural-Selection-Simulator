/**
 * 
 */
package model.entity.organism;

import java.util.EnumMap;

import model.mutation.Trait;
import model.mutation.TraitType;

/**
 * Interface that defines an Organism.
 *
 */
public interface Organism {

    /**
     * @return an EnumMap containing all organism's traits
     */
    EnumMap<TraitType, Trait> getTraits();
}
