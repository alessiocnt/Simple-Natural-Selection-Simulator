package mutation;

import static org.junit.Assert.fail;

import java.util.EnumMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.entity.EnergyImpl;
import model.entity.organism.Organism;
import model.entity.organism.OrganismBuilder;
import model.entity.organism.OrganismBuilderImpl;
import model.environment.AdvancedEnvironment;
import model.environment.EnvironmentFactoryImpl;
import model.environment.temperature.TemperatureImpl;
import model.mutation.TraitType;
import model.mutation.factory.MutatedOrganismFactory;
import model.mutation.factory.MutatedOrganismFactoryImpl;
import model.mutation.trait.ChildrenQuantity;
import model.mutation.trait.Dimension;
import model.mutation.trait.FoodRadar;
import model.mutation.trait.Speed;
import model.mutation.trait.TemperatureSensibility;
import model.mutation.trait.Trait;

/**
 * Test Mutated Factory.
 */
public class TestMutatedFactory {
    private static final int SPEEDINITIAL = 5;
    private static final int DIMENSIONINITIAL = 100;
    private static final int CHILDRENINITIAL = 2;
    private static final int FOODRADAR = 2;
    private Organism organism;
    private MutatedOrganismFactory factory;
    /**
     * Initialise traits.
     */
    @Before
    public void initialise() {
        final Map<TraitType, Trait> traits = new EnumMap<>(TraitType.class);
        traits.put(TraitType.SPEED, new Speed(TestMutatedFactory.SPEEDINITIAL));
        traits.put(TraitType.DIMENSION, new Dimension(TestMutatedFactory.DIMENSIONINITIAL));
        traits.put(TraitType.CHILDRENQUANTITY, new ChildrenQuantity(TestMutatedFactory.CHILDRENINITIAL));
        traits.put(TraitType.FOODRADAR, new FoodRadar(TestMutatedFactory.FOODRADAR));
        traits.put(TraitType.TEMPERATURESENSIBILITY, new TemperatureSensibility());
        final AdvancedEnvironment environment = new EnvironmentFactoryImpl()
                .createAdvancedEnviroment(100, 100, 100, 0, new TemperatureImpl(10));
        final OrganismBuilder builder = new OrganismBuilderImpl(new EnergyImpl(TestMutatedFactory.DIMENSIONINITIAL))
                .setEnvironmentKnowledge(environment);
        //Set all the trait for the builder.
        traits.entrySet()
            .stream()
            .forEach((entrySet) -> builder.setTrait(entrySet.getKey(), entrySet.getValue()));
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
