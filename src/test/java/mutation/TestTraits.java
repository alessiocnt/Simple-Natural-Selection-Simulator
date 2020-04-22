package mutation;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.EnumMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.EnergyImpl;
import model.entity.organism.Organism;
import model.entity.organism.OrganismBuilder;
import model.entity.organism.OrganismBuilderImpl;
import model.environment.AdvancedEnvironment;
import model.environment.factory.EnvironmentFactoryImpl;
import model.environment.temperature.TemperatureImpl;
import model.mutation.TraitType;
import model.mutation.trait.ChildrenQuantity;
import model.mutation.trait.Dimension;
import model.mutation.trait.FoodRadar;
import model.mutation.trait.Speed;
import model.mutation.trait.TemperatureSensibility;
import model.mutation.trait.Trait;

/**
 * Test of traits.
 *
 */
public class TestTraits {
    //Valori iniziali trait.
    private static final int SPEEDINITIAL = 5;
    private static final int DIMENSIONINITIAL = 100;
    private static final int CHILDRENINITIAL = 2;
    private static final int FOODRADAR = 2;

    //Consumo di cibo aspettato
    private static final int EXPTEMPSENSCONS = 2;
    private static final int EXPFOODRADARCONS = 4;
    private static final int EXPSPEEDCONS = 25;
    private static final int EXPDIMCONS = 10;
    private static final int EXPCHILDCONS = 40;

    private final Map<TraitType, Trait> traits = new EnumMap<>(TraitType.class);
    private Organism organism;
    /**
     * Initialise traits.
     */
    @BeforeEach
    public void initialise() {
        this.traits.put(TraitType.SPEED, new Speed(TestTraits.SPEEDINITIAL));
        this.traits.put(TraitType.DIMENSION, new Dimension(TestTraits.DIMENSIONINITIAL));
        this.traits.put(TraitType.CHILDRENQUANTITY, new ChildrenQuantity(TestTraits.CHILDRENINITIAL));
        this.traits.put(TraitType.FOODRADAR, new FoodRadar(TestTraits.FOODRADAR));
        this.traits.put(TraitType.TEMPERATURESENSIBILITY, new TemperatureSensibility());
        final AdvancedEnvironment environment = new EnvironmentFactoryImpl()
                .createAdvancedEnviroment(100, 100, 100, 0, new TemperatureImpl(10));
        final OrganismBuilder builder = new OrganismBuilderImpl(new EnergyImpl(TestTraits.DIMENSIONINITIAL))
                .setEnvironmentKnowledge(environment);
        //Set all the trait for the builder.
        this.traits.entrySet()
            .stream()
            .forEach(entrySet -> builder.setTrait(entrySet.getValue()));
        this.organism = builder.build();
    }

    /**
     * Method to test Trait values.
     */
    @Test
    public void testValues() {
        assertEquals(TestTraits.SPEEDINITIAL, this.traits.get(TraitType.SPEED).getValue());
        assertEquals(TestTraits.DIMENSIONINITIAL, this.traits.get(TraitType.DIMENSION).getValue());
        assertEquals(TestTraits.CHILDRENINITIAL, this.traits.get(TraitType.CHILDRENQUANTITY).getValue());
        assertEquals(TestTraits.FOODRADAR, this.traits.get(TraitType.FOODRADAR).getValue());
    }

    /**
     * Method to test the getFoodConsumption() of traits.
     */
    @Test
    public void testFoodConsuption() {
        assertEquals(new EnergyImpl(TestTraits.EXPSPEEDCONS), this.traits.get(TraitType.SPEED).getFoodConsumption(organism));
        assertEquals(new EnergyImpl(TestTraits.EXPDIMCONS), this.traits.get(TraitType.DIMENSION).getFoodConsumption(organism));
        assertEquals(new EnergyImpl(TestTraits.EXPCHILDCONS), this.traits.get(TraitType.CHILDRENQUANTITY).getFoodConsumption(organism));
        assertEquals(new EnergyImpl(TestTraits.EXPFOODRADARCONS), this.traits.get(TraitType.FOODRADAR).getFoodConsumption(organism));
        assertEquals(new EnergyImpl(TestTraits.EXPTEMPSENSCONS), this.traits.get(TraitType.TEMPERATURESENSIBILITY).getFoodConsumption(organism));
    }
}
