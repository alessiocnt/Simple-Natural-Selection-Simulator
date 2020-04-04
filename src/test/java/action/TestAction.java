package action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import controller.action.strategy.EatLogics;
import controller.action.strategy.EatLogicsImpl;
import controller.action.strategy.MoveLogics;
import controller.action.strategy.MoveLogicsImpl;
import controller.action.strategy.ReplicateLogics;
import controller.action.strategy.ReplicateLogicsImpl;
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
import model.mutation.trait.ChildrenQuantity;
import model.mutation.trait.Dimension;
import model.mutation.trait.Speed;
import model.mutation.trait.Trait;

class TestAction {

    private static final Energy INITIALENERGY = new EnergyImpl(1000);
    private static final int INITIALSPEED = 5;
    private static final int INITIALDIMENSION = 100;
    private static final int INITIALCHILDRENQTY = 2;
    private static final double TEMPERATURE = 25;
    private static final int EXPECTEDMOVEMENTCONSUPTION = 35;
    private static final int EXPECTEDREPLICATIONCONSUPTION = 40;

    private Trait speed = new Speed(INITIALSPEED);
    private Trait dimension = new Dimension(INITIALDIMENSION);
    private Trait children = new ChildrenQuantity(INITIALCHILDRENQTY);

    private final EnvironmentFactory factory = new EnvironmentFactoryImpl();
    private final OrganismBuilder organismBuilder = new OrganismBuilderImpl(INITIALENERGY);
    private AdvancedEnvironment environment = factory.createAdvancedEnviroment(100, 100, 10, 0, new TemperatureImpl(TEMPERATURE));
    private Organism organism = createOrganism();

    private EatLogics eatLogic = new EatLogicsImpl();
    private MoveLogics moveLogic = new MoveLogicsImpl();
    private ReplicateLogics replicateLogic = new ReplicateLogicsImpl();

    // Setting up an organism with basic traits.
    private Organism createOrganism() {
        organismBuilder.setTrait(TraitType.SPEED, speed);
        organismBuilder.setTrait(TraitType.DIMENSION, dimension);
        organismBuilder.setTrait(TraitType.CHILDRENQUANTITY, children);
        organismBuilder.setEnvironmentKnowledge(environment);
        this.environment.addOrganism(organismBuilder.build());
        return environment.getOrganisms().next();
    }

    @Test
    public void testComputeConsumptionForMovement() {
        Energy expectedEnergy = new EnergyImpl(EXPECTEDMOVEMENTCONSUPTION);
        Energy currentConsumption = moveLogic.computeConsumptionForMovement(organism);
        assertEquals(expectedEnergy, currentConsumption);
    }

    @Test
    public void testEnergyEnoughtToMove() {
        assertTrue(Energy.greater(organism.getEnergy(), moveLogic.computeConsumptionForMovement(organism))
                || organism.getEnergy().equals(moveLogic.computeConsumptionForMovement(organism)));
    }

    @Test
    public void testCanEat() {
        Set<Food> f1 = Collections.emptySet();
        Set<Food> f2 = new HashSet<>();
        f2.add(new FoodBuilderImpl().build());
        assertFalse(eatLogic.canEat(organism, f1));
        assertTrue(eatLogic.canEat(organism, f2));
    }

    @Test
    public void testEat() {
        Set<Food> f = new HashSet<>();
        f.add(new FoodBuilderImpl().build());
        organism.setEnergy(new EnergyImpl(0));
        eatLogic.eat(organism, f);
        assertTrue(organism.getEnergy().getEnergy() == 100);
    }

    @Test
    public void testReplicate() {
        assertNotEquals(replicateLogic.replicate(organism), organism);
    }

    @Test
    public void testComputeConsumptionForReplication() {
        Energy expectedEnergy = new EnergyImpl(EXPECTEDREPLICATIONCONSUPTION);
        Energy currentConsumption = replicateLogic.computeConsumptionForReplication(organism);
        assertEquals(expectedEnergy, currentConsumption);
    }
}
