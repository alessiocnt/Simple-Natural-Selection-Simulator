/**
 * 
 */
package model.entity.organism;

import java.util.Map;
import model.entity.Entity;
import model.environment.holders.OrganismEnvironmentHolder;
import model.mutation.TraitType;
import model.mutation.trait.Trait;

/**
 * Interface that defines an Organism.
 *
 */
public interface Organism extends Entity {

    /**
     * @return a Map containing all organism's traits
     */
    Map<TraitType, Trait> getTraits();

    /**
     * @return an OrganismEnvironmentHolder containing all the information that an organism knows about the environment
     */
    OrganismEnvironmentHolder getEnvironmentKnowledge();
}
