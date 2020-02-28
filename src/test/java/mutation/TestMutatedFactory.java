package mutation;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.entity.EnergyImpl;
import model.entity.organism.Organism;
import model.entity.organism.OrganismBuilder;
import model.entity.organism.OrganismBuilderImpl;
import model.mutation.ChildrenQuantity;
import model.mutation.Dimension;
import model.mutation.Speed;
import model.mutation.Trait;
import model.mutation.TraitType;
import model.mutation.factory.MutatedOrganismFactory;
import model.mutation.factory.MutatedOrganismFactoryImpl;

/**
 * Test Mutated Factory.
 */
public class TestMutatedFactory {
    private static final int SPEEDINITIAL = 5;
    private static final int DIMENSIONINITIAL = 100;
    private static final int CHILDRENINITIAL = 2;
    private Organism organism;
    private MutatedOrganismFactory factory;
    /**
     * Initialise traits.
     */
    @Before
    public void initialise() {
        final Trait speed = new Speed(TestMutatedFactory.SPEEDINITIAL);
        final Trait dimension = new Dimension(TestMutatedFactory.DIMENSIONINITIAL);
        final Trait childrenQuantity = new ChildrenQuantity(TestMutatedFactory.CHILDRENINITIAL);
        final OrganismBuilder builder = new OrganismBuilderImpl(new EnergyImpl(TestMutatedFactory.DIMENSIONINITIAL));
        builder.trait(TraitType.SPEED, speed);
        builder.trait(TraitType.DIMENSION, dimension);
        builder.trait(TraitType.CHILDRENQUANTITY, childrenQuantity);
        this.organism = builder.build();
        this.factory = new MutatedOrganismFactoryImpl();
    }

    /**
     * Test that dad and children are different organism.
     */
    @Test
    public void testMutatedOrganism() {
        final Organism children = this.factory.createMutated(this.organism);
        if (children == this.organism) {
            //Children and dad have the same reference, so are the same organism.
            fail();
        }
    }
}
