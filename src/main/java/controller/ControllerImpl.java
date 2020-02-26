package controller;

import settings.DayDuration;

/**
 * Controller implementation.
 *
 */
public class ControllerImpl implements Controller {

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
                waitForNextFrame(dayDuration, elapsed);
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
