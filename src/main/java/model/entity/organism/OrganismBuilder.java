/**
 * 
 */
package model.entity.organism;

import model.environment.holders.OrganismEnvironmentHolder;
import model.mutation.TraitType;
import model.mutation.trait.Trait;

/**
 * Interface that models an organism's builder.
 *
 */
public interface OrganismBuilder {

    /**
     * @param type the type of mutation
     * @param trait the proper trait that must be associated to the organism
     * @return an OrganismBuilderImpl
     */
    OrganismBuilderImpl setTrait(TraitType type, Trait trait);

    /**
     * @param environmentKnowledge organism knowledges an organism has about the environment
     * @return an OrganismBuilderImpl
     */
    OrganismBuilderImpl setEnvironmentKnowledge(OrganismEnvironmentHolder environmentKnowledge);

    /**
     * @return an instance of OrganismImpl if all the fields are not null
     * @throws IllegalArgumentException instead
     */
    Organism build();
}
