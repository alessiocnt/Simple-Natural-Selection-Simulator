package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.EnumMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.Energy;
import model.entity.EnergyImpl;
import model.entity.food.Food;
import model.entity.food.FoodBuilderImpl;
import model.entity.organism.Organism;
import model.entity.organism.OrganismBuilder;
import model.entity.organism.OrganismBuilderImpl;
import model.environment.AdvancedEnvironment;
import model.environment.EnvironmentFactory;
import model.environment.EnvironmentFactoryImpl;
import model.environment.temperature.TemperatureImpl;
import model.mutation.TraitType;
import model.mutation.trait.Dimension;
import model.mutation.trait.Speed;
import model.mutation.trait.Trait;

class TestEntity {

    private static final Energy INITIALENERGY = new EnergyImpl(10);
    private static final Energy INITIALFOODENERGY = new EnergyImpl(100);
    private static final int INITIALSPEED = 3;
    private static final int INITIALDIMENSION = 50;
    private static final double TEMPERATURE = 50;

    private Trait speed;
    private Trait dimension;

    @BeforeEach
    public void initialise() {
        speed = new Speed(INITIALSPEED);
        dimension = new Dimension(INITIALDIMENSION);
    }

    @Test
    public void testOrganismBuilder() {
        final EnvironmentFactory factory = new EnvironmentFactoryImpl();
        final AdvancedEnvironment environment = factory.createAdvancedEnviroment(100, 100, 10, 0, new TemperatureImpl(TEMPERATURE));
        final OrganismBuilder organismBuilder = new OrganismBuilderImpl(INITIALENERGY);
        organismBuilder.setTrait(TraitType.SPEED, speed);
        organismBuilder.setTrait(TraitType.DIMENSION, dimension);
        organismBuilder.setEnvironmentKnowledge(environment);
        environment.addOrganism(organismBuilder.build());
        final Organism organism = environment.getOrganisms().next();

        assertEquals(INITIALENERGY, organism.getEnergy());
        final EnumMap<TraitType, Trait> expectedTraits = new EnumMap<>(TraitType.class);
        expectedTraits.put(TraitType.SPEED, this.speed);
        expectedTraits.put(TraitType.DIMENSION, this.dimension);
        assertEquals(expectedTraits, organism.getTraits());
    }

    @Test
    public void testExceptionInOrganismBuild() {
        @SuppressWarnings("unused")
        Organism o1;
        try {
            o1 = new OrganismBuilderImpl(INITIALENERGY).build();
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException exception) {
            assertTrue(exception.getMessage().equals("Argument can not be null."));
        }
    }

    @Test
    public void testFoodBuilder() {
        final Food f1 = new FoodBuilderImpl().build();
        assertNotEquals(INITIALENERGY, f1.getEnergy());
        assertEquals(INITIALFOODENERGY, f1.getEnergy());
    }
}
