package controller;

import java.util.Iterator;

import model.Model;
import model.entity.food.FoodBuilder;
import model.entity.organism.Organism;
import model.environment.AdvancedEnvironment;
import model.environment.daycicle.DayCicle;
import model.environment.daycicle.DayCicleImpl;
import model.environment.daycicle.DayPeriod;
import model.environment.position.Position;
import model.environment.temperature.Temperature;
import settings.DayDuration;
import settings.Settings;
import settings.SettingsHolder;
import settings.SettingsImpl;
import view.View;
import view.entities.EnvironmentHolder;

/**
 * Controller implementation.
 *
 */
public class ControllerImpl implements Controller {
    private final Model model;
    private final View view;
    private final Settings settings = new SettingsImpl();
    private SimulationLoop simulationLoop;

    /**
     * @param model
     *                  model of the app.
     * @param view
     *                  view of the app.
     */
    public ControllerImpl(final Model model, final View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final DayCicle dayCicle) {
        AdvancedEnvironment environment = this.model.getEnvironment();
        dayCicle.nextTick();
        // Updates the environment food
        updateEnvironmentFood(dayCicle.getCurrentDayMoment(), environment);
        Iterator<Organism> organisms = environment.getOrganisms();
        while (organisms.hasNext()) {
            this.model.getActionController().getActions().get(dayCicle.getCurrentDayMoment()).perform(organisms.next());
        }
    }

    private void updateEnvironmentFood(final DayPeriod currentDayMoment, final AdvancedEnvironment environment) {
        FoodBuilder foodBuilder = this.model.getFoodBuilder();
        // if it's night refills the environment with food for the next day
        if (currentDayMoment == DayPeriod.NIGHT) {
            //tells the environment a new day is coming
            environment.nextDay();
            for (int i = 0; i < environment.getMorningFoodQuantity(); i++) {
                environment.addFood(foodBuilder.build());
            }
        }
    }
    @Override
    public final void setEnvironmentInitialValues(final EnvironmentHolder holder) {
        this.model.prepareEnvironment(holder);
    }

    @Override
    public final boolean initSimulation() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void startSimulation() {
        this.simulationLoop = new SimulationLoop();
        this.simulationLoop.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startStopSimulation() {
        this.simulationLoop.startStop();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSimulationRunning() {
        return this.simulationLoop.running;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getEnvironmentDimension() {
        return this.model.getEnvironmentDimension();
    }

    @Override
    public final void setDayDuration(final DayDuration duration) {
        this.settings.setDayDuration(duration);
    }

    @Override
    public final void setWidth(final int width) {
        this.settings.setWidth(width);
    }

    @Override
    public final void setHeight(final int height) {
        this.settings.setHeight(height);
    }

    @Override
    public final SettingsHolder getSettings() {
        return this.settings;
    }

    private class SimulationLoop extends Thread {
        private static final int UPDATES_IN_A_DAY = 10;
        private volatile boolean running;
        private DayCicle dayCicle = new DayCicleImpl(UPDATES_IN_A_DAY);
        private Object mutex = new Object();

        SimulationLoop() {
            this.setDaemon(true);
            this.running = true;
        }

        public void run() {
            while (this.running && !model.isSimulationOver()) {
                final long startTime = System.currentTimeMillis();
                update();
                render();
                final int elapsedTime = (int) (System.currentTimeMillis() - startTime);
                waitForNextFrame(settings.getDayDuration(), elapsedTime);
                synchronized (this.mutex) {
                    try {
                        if (!this.running) {
                            this.mutex.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void update() {
            ControllerImpl.this.update(dayCicle);
            if (model.isSimulationOver()) {
                view.setSimulationOver();
            }
        }

        private void render() {
            view.render(model.getFoods(), model.getOrganisms());
        }

        private void waitForNextFrame(final DayDuration dayDuration, final int elapsed) {
            int timeUntilNextLoop = (dayDuration.getDuration() * 1000 / UPDATES_IN_A_DAY) - elapsed;
            // the sleep time cannot be < 0, this would cause an exception
            if (timeUntilNextLoop > 0) {
                try {
                    Thread.sleep(timeUntilNextLoop);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void startStop() {
            this.running = !this.running;
            synchronized (this.mutex) {
                if (this.running) {
                    this.mutex.notify();
                }
            }
        }
    }

}
