package controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
            model.update(dayCicle);
            Platform.runLater(() -> {
                if (model.isSimulationOver()) {
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setTitle("INFO");
                    a.setContentText("The simulation is over, everyone is dead.");
                    a.show();
                }
            });
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
