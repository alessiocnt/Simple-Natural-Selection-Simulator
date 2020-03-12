package environment;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.entity.EnergyImpl;
import model.entity.food.Food;
import model.entity.food.FoodBuilder;
import model.entity.food.FoodBuilderImpl;
import model.entity.organism.Organism;
import model.entity.organism.OrganismBuilder;
import model.entity.organism.OrganismBuilderImpl;
import model.environment.BasicEnvironment;
import model.environment.EnvironmentFactory;
import model.environment.EnvironmentFactoryImpl;
import model.mutation.ChildrenQuantity;
import model.mutation.Dimension;
import model.mutation.Speed;
import model.mutation.Trait;
import model.mutation.TraitType;

public class TestBasicEnvironment {

    private static final int X_DIMENSION = 100;
    private static final int Y_DIMENSION = 100;
    private static final int INITIAL_FOOD = 100;
    private final int speed = 5;
    private final int dimension = 100;
    private final int children = 2;
    private EnvironmentFactory factory; 
    private BasicEnvironment environment;
    private OrganismBuilder organismBuilder;
    private Organism organism;
    private Food food;


    @Before
    public void initialize() {
        this.factory = new EnvironmentFactoryImpl();
        this.environment = this.factory.createBasicEnviroment(X_DIMENSION, Y_DIMENSION, INITIAL_FOOD, 0);
        this.organismBuilder = new OrganismBuilderImpl(new EnergyImpl(100));
        final Trait speed = new Speed(this.speed);
        final Trait dimension = new Dimension(this.dimension);
        final Trait childrenQuantity = new ChildrenQuantity(this.children);
        final FoodBuilder foodBuilder = new FoodBuilderImpl();
        this.organismBuilder.trait(TraitType.SPEED, speed);
        this.organismBuilder.trait(TraitType.DIMENSION, dimension);
        this.organismBuilder.trait(TraitType.CHILDRENQUANTITY, childrenQuantity);
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
        assertEquals(5, this.environment.getCurrendOrganismQuantity());
    }

    @Test 
    public void testAddFood() {
        assertEquals(5, this.environment.getCurrentFoodQuantity());
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
        assertEquals(6, this.environment.getCurrendOrganismQuantity());
    }

    @Test
    public void testNewDay() {
        this.environment.nextDay();
        assertEquals(0, this.environment.getCurrentFoodQuantity());
    }
}

