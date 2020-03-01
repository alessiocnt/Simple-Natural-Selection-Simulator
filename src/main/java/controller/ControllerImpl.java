package controller;


import model.Model;
import model.environment.daycicle.DayCicle;
import model.environment.daycicle.DayCicleImpl;
import model.environment.position.Position;
import settings.DayDuration;
import settings.Settings;
import settings.SettingsHolder;
import settings.SettingsImpl;
import view.View;
import view.entities.EnvironmentHolder;
import settings.DayDuration;

/**
 * Controller implementation.
 *
 */
public class ControllerImpl implements Controller {
    private final Model model;
    private final View view;
    private final Settings settings = new SettingsImpl();
    private final SimulationLoop simulationLoop = new SimulationLoop();

    /**
     * @param model
     * model of the app.
     * @param view
     * view of the app.
     */
    public ControllerImpl(final Model model, final View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public final void setEnvironmentInitialValues(final EnvironmentHolder holder) {
        // TODO Auto-generated method stub
    }

    @Override
    public final boolean initSimulation() {
        return true;
    }

    @Override
    public final void startSimulation() {
        //this.simulationLoop.start();
        this.view.render(null, null);
    }

    @Override
    public final void stopSimulation() {
        this.simulationLoop.stopSimulationLoop();
    }

    @Override
    public final void setDayDuration(final DayDuration duration) {
        this.settings.setDayDuration(duration);
    }

    @Override
    public final SettingsHolder getSettings() {
        return this.settings;
    }

    private class SimulationLoop extends Thread {
        private static final int UPDATES_IN_A_DAY = 100;
        private volatile boolean running;
        private DayCicle dayCicle = new DayCicleImpl(UPDATES_IN_A_DAY);

        SimulationLoop() {
            this.running = true;
        }

        public void run() {
            while (this.running && !model.isSimulationOver()) {
                final long startTime = System.currentTimeMillis();
                update();
                render();
                final int elapsedTime = (int) (System.currentTimeMillis() - startTime);
                waitForNextFrame(settings.getDayDuration(), elapsedTime);
            }
        }



        private void update() {
            // TODO Auto-generated method stub
            //model.update(dayCicle);
            System.out.println("Update");
        }

        private void render() {
            // TODO Auto-generated method stub
            view.render(model.getFoods(), model.getOrganisms());
            System.out.println("Render");
        }

        private void waitForNextFrame(final DayDuration dayDuration, final int elapsed) {
            int timeUntilNextLoop = (dayDuration.getDuration() * 1000 / UPDATES_IN_A_DAY) - elapsed;
            //the sleep time cannot be < 0, this would cause an exception
            if (timeUntilNextLoop > 0) {
                try {
                    Thread.sleep(timeUntilNextLoop);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopSimulationLoop() {
            this.running = false;
        }

        public void resumeSimulationLoop() {
            this.running = true;
            super.start();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getEnvironmentDimension() {
        return this.model.getEnvironmentDimension();
    }
}

