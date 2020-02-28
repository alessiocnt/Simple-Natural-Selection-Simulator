package entity;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumMap;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.entity.Energy;
import model.entity.EnergyImpl;
import model.entity.food.Food;
import model.entity.food.FoodBuilderImpl;
import model.entity.organism.Organism;
import model.entity.organism.OrganismBuilderImpl;
import model.mutation.Dimension;
import model.mutation.Speed;
import model.mutation.Trait;
import model.mutation.TraitType;

class TestEntity {

    private static final Energy INITIALENERGY = new EnergyImpl(10);
    private static final Energy INITIALFOODENERGY = new EnergyImpl(50);
    private static final int INITIALSPEED = 3;
    private static final int INITIALDIMENSION = 50;

    private Trait speed;
    private Trait dimension;

    @Before
    public void initialise() {
        speed = new Speed(INITIALSPEED);
        dimension = new Dimension(INITIALDIMENSION);
    }

    @Test
    public void testOrganismBuilder() {
        Organism o1 = new OrganismBuilderImpl(INITIALENERGY).trait(TraitType.SPEED, this.speed).trait(TraitType.DIMENSION, this.dimension).build();
        assertEquals(INITIALENERGY, o1.getEnergy());
        EnumMap<TraitType, Trait> expectedTraits = new EnumMap<>(TraitType.class);
        expectedTraits.put(TraitType.SPEED, this.speed);
        expectedTraits.put(TraitType.DIMENSION, this.dimension);
        assertEquals(expectedTraits, o1.getTraits());
    }

    @Test
    public void testExceptionInOrganismBuild() {
        @SuppressWarnings("unused")
        Organism o1;
        try {
            o1 = new OrganismBuilderImpl(INITIALENERGY).build();
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException exception) {
            assertTrue(exception.getMessage() == "Argument can not be null.");
        }
    }

    @Test
    public void testFoodBuilder() {
        Food f1 = new FoodBuilderImpl().build();
        assertNotEquals(INITIALENERGY, f1.getEnergy());
        assertEquals(INITIALFOODENERGY, f1.getEnergy());
    }
}
