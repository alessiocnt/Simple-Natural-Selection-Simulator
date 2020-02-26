package controller;


import model.Model;
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public final void startSimulation() {
        // TODO Auto-generated method stub
    }

    @Override
    public final void stopSimulation() {
        // TODO Auto-generated method stub
    }

    @Override
    public final void setDayDuration(final DayDuration duration) {
        // TODO Auto-generated method stub
    }

    @Override
    public final SettingsHolder getSettings() {
        return this.settings;
    }

    private class SimulationLoop extends Thread {
        private static final int UPDATES_IN_A_DAY = 100;
        private volatile boolean running;

        public SimulationLoop() {
            this.running = true;
        }

        public void run() {
            while (this.running /*TODO uncomment && !model.isSimulationOver()*/) {
                final long startTime = System.currentTimeMillis();
                update();
                render();
                final int elapsedTime = (int) (System.currentTimeMillis() - startTime);
                //waitForNextFrame(dayDuration, elapsedTime);
            }
        }



        private void update() {
            // TODO Auto-generated method stub
            //model.update();
            System.out.println("Update");
        }

        private void render() {
            // TODO Auto-generated method stub
            //view.render();
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
}

