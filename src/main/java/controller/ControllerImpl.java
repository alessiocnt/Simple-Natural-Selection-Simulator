package controller;


import model.Model;
import settings.DayDuration;
import settings.SettingsHolder;
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
        // TODO Auto-generated method stub
        return null;
    }

    private class SimulationLoop extends Thread {
        private static final int UPDATES_IN_A_DAY = 100;
        private volatile boolean running;

        public SimulationLoop() {
            this.running = true;
        }

        public void run() {
            long lastTime = System.currentTimeMillis();
            while (this.running /*TODO uncomment && !model.isSimulationOver()*/) {
                final long current = System.currentTimeMillis();
                final int elapsed = (int) (current - lastTime);
                update();
                render();
                //waitForNextFrame(dayDuration, elapsed);
            }
        }



        private void update() {
            // TODO Auto-generated method stub
        }
        private void render() {
            // TODO Auto-generated method stub
        }
        private void waitForNextFrame(DayDuration dayDuration, int elapsed) {
            try {
                Thread.sleep((dayDuration.getDuration()/UPDATES_IN_A_DAY) - elapsed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

