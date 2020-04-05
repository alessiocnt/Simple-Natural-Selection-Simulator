package environment;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

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

import model.environment.OrganismEnvironmentHolder;
import model.environment.temperature.Temperature;
import model.environment.temperature.TemperatureImpl;
import model.mutation.TraitType;
import model.mutation.trait.ChildrenQuantity;
import model.mutation.trait.Dimension;
import model.mutation.trait.FoodRadar;
import model.mutation.trait.Speed;
import model.mutation.trait.Trait;

public class TestEnvironment {

    private static final int X_DIMENSION = 100;
    private static final int Y_DIMENSION = 100;
    private static final int INITIAL_FOOD = 100;
    private final int speed = 5;
    private final int dimension = 100;
    private final int children = 2;
    private final int foodRadar = 1;
    private final Temperature temperature = new TemperatureImpl(20);
    private EnvironmentFactory factory; 
    private AdvancedEnvironment environment;
    private OrganismBuilder organismBuilder;
    private Organism organism;
    private Food food;


    @Before
    public void initialize() {
        this.factory = new EnvironmentFactoryImpl();
        this.environment = this.factory.createAdvancedEnviroment(X_DIMENSION, Y_DIMENSION, INITIAL_FOOD, 0, this.temperature);
        this.organismBuilder = new OrganismBuilderImpl(new EnergyImpl(100));
        final Trait speed = new Speed(this.speed);
        final Trait dimension = new Dimension(this.dimension);
        final Trait childrenQuantity = new ChildrenQuantity(this.children);
        final Trait foodRadar = new FoodRadar(this.foodRadar);
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

    @Test
    public void testAddOrganism() {
        final int expected = 5;
        assertEquals(expected, this.environment.getCurrendOrganismQuantity());
    }

    @Test 
    public void testAddFood() {
        final int expected = 5;
        assertEquals(expected, this.environment.getCurrentFoodQuantity());
    }

    @Test
    public void removeFoodAndOrganism() {
        this.environment.removeFood(this.food);
        this.environment.removeOrganism(this.organism);
        assertEquals(4, this.environment.getCurrentFoodQuantity());
        assertEquals(4, this.environment.getCurrendOrganismQuantity());
    }
    @Test
    public void testAddSon() {
        this.environment.addOrganism(this.environment.getOrganisms().next(), this.organismBuilder.build());
        final int expected = 6;
        assertEquals(expected, this.environment.getCurrendOrganismQuantity());
    }

    @Test
    public void testNewDay() {
        this.environment.nextDay();
        assertEquals(0, this.environment.getCurrentFoodQuantity());
    }

    @Test
    public void testTemperature() {
        final Temperature expected = this.temperature;
        assertEquals(expected, (this.environment).getTemperature());
    }
}
