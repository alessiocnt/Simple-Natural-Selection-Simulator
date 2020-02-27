package model.mutation.factory;

import model.entity.organism.Organism;

/**
 * Interface of mutated children factory.
 */
public interface MutatedOrganismFactory {
    /**
     * @param organism
     * Dad organism.
     * Children is created based on dad's traits value.
     * @return
     * The children created.
     */
    Organism createMutated(Organism organism);
}
