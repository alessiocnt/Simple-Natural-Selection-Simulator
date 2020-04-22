/**
 * 
 */
package model.entity.organism;

import model.environment.holders.OrganismEnvironmentHolder;
import model.mutation.trait.Trait;

/**
 * Interface that models an organism's builder.
 *
 */
public interface OrganismBuilder {

    /**
     * @param trait the proper trait that must be associated to the organism
     * @return an OrganismBuilder
     */
    OrganismBuilder setTrait(Trait trait);

    /**
     * @param environmentKnowledge organism knowledges an organism has about the environment
     * @return an OrganismBuilder
     */
    OrganismBuilder setEnvironmentKnowledge(OrganismEnvironmentHolder environmentKnowledge);

    /**
     * @return an instance of OrganismImpl if all the fields are not null
     */
    Organism build();
}
