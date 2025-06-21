package game;

public class Clock {

    private int seconds;
    private volatile boolean active; // makes visible between threads
    private Thread thread;

    public Clock(int initialTimeSec) {
        this.seconds = initialTimeSec;
        this.active = false;
    }

    public int getTime() {
        return seconds;
    }

    public void startClock() {
        // if it's already active and thread is alive, do nothing
        if (thread != null && thread.isAlive() && active)
            return;

        active = true;
        thread = new Thread(() -> {
            while (active && seconds > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                seconds--;
            }
        }, "Clock-Thread");
        thread.setDaemon(true);
        thread.start();
    }

    public void stopClock() {
        // ATTENTION: the clock does not stop immediatelly due to how the threads work -
        // it may take up to one second for it to stop
        active = false;
    }

    public String formatTime() {
        int min = seconds / 60;
        int seg = seconds % 60;
        return String.format("%02d:%02d", min, seg);
    }

}
