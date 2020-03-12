package mutation;

import static org.junit.Assert.assertEquals;

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

/**
 * Test of traits.
 *
 */
public class TestTraits {
    private static final int SPEEDINITIAL = 5;
    private static final int DIMENSIONINITIAL = 100;
    private static final int CHILDRENINITIAL = 2;
    private static final int EXPSPEEDCONS = 25;
    private static final int EXPDIMCONS = 10;
    private static final int EXPCHILDCONS = 20;

    private Trait speed;
    private Trait dimension;
    private Trait childrenQuantity;
    private Organism organism;
    /**
     * Initialise traits.
     */
    @Before
    public void initialise() {
        speed = new Speed(TestTraits.SPEEDINITIAL);
        dimension = new Dimension(TestTraits.DIMENSIONINITIAL);
        childrenQuantity = new ChildrenQuantity(TestTraits.CHILDRENINITIAL);
        final OrganismBuilder builder = new OrganismBuilderImpl(new EnergyImpl(TestTraits.DIMENSIONINITIAL));
        builder.setTrait(TraitType.SPEED, this.speed);
        builder.setTrait(TraitType.DIMENSION, this.dimension);
        builder.setTrait(TraitType.CHILDRENQUANTITY, this.childrenQuantity);
        this.organism = builder.build();
    }

    /**
     * Method to test Trait values.
     */
    @Test
    public void testValues() {
        assertEquals(TestTraits.SPEEDINITIAL, this.speed.getValue());
        assertEquals(TestTraits.DIMENSIONINITIAL, this.dimension.getValue());
        assertEquals(TestTraits.CHILDRENINITIAL, this.childrenQuantity.getValue());
    }

    /**
     * Method to test the getFoodConsumption() of traits.
     */
    @Test
    public void testFoodConsuption() {
        assertEquals(new EnergyImpl(TestTraits.EXPSPEEDCONS), this.speed.getFoodConsumption(organism));
        assertEquals(new EnergyImpl(TestTraits.EXPDIMCONS), this.dimension.getFoodConsumption(organism));
        assertEquals(new EnergyImpl(TestTraits.EXPCHILDCONS), this.childrenQuantity.getFoodConsumption(organism));
    }
}
