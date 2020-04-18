package environment;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.EnergyImpl;
import model.entity.food.Food;
import model.entity.food.FoodBuilder;
import model.entity.food.FoodBuilderImpl;
import model.entity.organism.Organism;
import model.entity.organism.OrganismBuilder;
import model.entity.organism.OrganismBuilderImpl;
import model.environment.AdvancedEnvironment;
import model.environment.EnvironmentFactory;
import model.environment.EnvironmentFactoryImpl;

import model.environment.temperature.Temperature;
import model.environment.temperature.TemperatureImpl;
import model.mutation.TraitType;
import model.mutation.trait.ChildrenQuantity;
import model.mutation.trait.Dimension;
import model.mutation.trait.FoodRadar;
import model.mutation.trait.Speed;
import model.mutation.trait.Trait;

/**
 * Environment test.
 *
 */
public class TestEnvironment {

    private static final int X_DIMENSION = 100;
    private static final int Y_DIMENSION = 100;
    private static final int INITIAL_FOOD = 100;
    private static final int SPEED = 5;
    private static final int DIMENSION = 100;
    private static final int CHILDREN = 2;
    private static final int FOOD_RADAR = 1;
    private final Temperature temperature = new TemperatureImpl(20);
    private AdvancedEnvironment environment;
    private OrganismBuilder organismBuilder;
    private Organism organism;
    private Food food;


    /**
     * Initialize tests.
     */
    @BeforeEach
    public final void initialize() {
        final EnvironmentFactory factory = new EnvironmentFactoryImpl();
        this.environment = factory.createAdvancedEnviroment(X_DIMENSION, Y_DIMENSION, INITIAL_FOOD, 0, this.temperature);
        this.organismBuilder = new OrganismBuilderImpl(new EnergyImpl(100));
        final Trait speed = new Speed(TestEnvironment.SPEED);
        final Trait dimension = new Dimension(TestEnvironment.DIMENSION);
        final Trait childrenQuantity = new ChildrenQuantity(TestEnvironment.CHILDREN);
        final Trait foodRadar = new FoodRadar(TestEnvironment.FOOD_RADAR);
        final FoodBuilder foodBuilder = new FoodBuilderImpl();
        this.organismBuilder.setTrait(TraitType.SPEED, speed);
        this.organismBuilder.setTrait(TraitType.DIMENSION, dimension);
        this.organismBuilder.setTrait(TraitType.CHILDRENQUANTITY, childrenQuantity);
        this.organismBuilder.setTrait(TraitType.FOODRADAR, foodRadar);
        this.organismBuilder.setEnvironmentKnowledge(this.environment);
        this.organism = this.organismBuilder.build();
        this.environment.addOrganism(this.organism);
        this.food = foodBuilder.build();
        this.environment.addFood(this.food);
        for (int i = 0; i < 4; i++) {
            this.environment.addOrganism(this.organismBuilder.build());
            this.environment.addFood(foodBuilder.build());
        }
    }

    /**
     * Test the organism insertion.
     */
    @Test
    public void testAddOrganism() {
        final int expected = 5;
        assertEquals(expected, this.environment.getCurrendOrganismQuantity());
    }

    /**
     * Test food insertion. 
     */
    @Test 
    public void testAddFood() {
        final int expected = 5;
        assertEquals(expected, this.environment.getCurrentFoodQuantity());
    }

    /**
     * Test food and organism removal.
     */
    @Test
    public void removeFoodAndOrganism() {
        this.environment.removeFood(this.food);
        this.environment.removeOrganism(this.organism);
        assertEquals(4, this.environment.getCurrentFoodQuantity());
        assertEquals(4, this.environment.getCurrendOrganismQuantity());
    }

    /**
     * Test son insertion. 
     */
    @Test
    public void testAddSon() {
        this.environment.addOrganism(this.environment.getOrganisms().next(), this.organismBuilder.build());
        final int expected = 6;
        assertEquals(expected, this.environment.getCurrendOrganismQuantity());
    }

    /**
     * Test new day.
     */
    @Test
    public void testNewDay() {
        this.environment.nextDay();
        assertEquals(0, this.environment.getCurrentFoodQuantity());
    }

    /**
     * Test temperature.
     */
    @Test
    public void testTemperature() {
        final Temperature expected = this.temperature;
        assertEquals(expected, this.environment.getTemperature());
    }
}

