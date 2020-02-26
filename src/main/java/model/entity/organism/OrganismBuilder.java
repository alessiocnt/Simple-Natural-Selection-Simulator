/**
 * 
 */
package model.entity.organism;

import model.mutation.Trait;
import model.mutation.TraitType;

/**
 * Interface that models an organism's builder.
 *
 */
public interface OrganismBuilder {

    /**
     * @param type the type of mutation
     * @param trait the proper trait that must be associated to the organism
     * @return an EnumMap containing all organism's traits
     */
    OrganismBuilderImpl trait(TraitType type, Trait trait);

    /**
     * @return an instance of OrganismImpl if all the fields are not null
     * @throws IllegalArgumentException instead
     */
    OrganismImpl build();
}
