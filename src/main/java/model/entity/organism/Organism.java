/**
 * 
 */
package model.entity.organism;

import java.util.EnumMap;

import model.entity.Entity;
import model.environment.OrganismEnvironmentHolder;
import model.mutation.Trait;
import model.mutation.TraitType;

/**
 * Interface that defines an Organism.
 *
 */
public interface Organism extends Entity {

    /**
     * @return an EnumMap containing all organism's traits
     */
    EnumMap<TraitType, Trait> getTraits();

    /**
     * @return an OrganismEnvironmentHolder containing all the information that an organism knows about the environment
     */
    OrganismEnvironmentHolder getEnvironmentKnowledge();
}
