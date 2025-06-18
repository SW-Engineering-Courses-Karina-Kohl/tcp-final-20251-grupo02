package game;
public class Clock {

    private int time;                 // in seconds
    private volatile boolean active;    // makes visible between threads
    private Thread thread;

    public Clock(int initialTime) {
        this.time = initialTime;
        this.active = false;
    }

    public void StartClock() {
	// if it's already active and thread is alive, do nothing
        if (thread != null && thread.isAlive()) return;

        active = true;
        thread = new Thread(() -> {
            while (active && time > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                time--;
            }
        }, "Clock-Thread");
        thread.setDaemon(true);
        thread.start();
    }

    public void StopClock() {
        active = false;
    }

    public String FormatTime() {
        int min = time / 60;
        int seg = time % 60;
        return String.format("%02d:%02d", min, seg);
    }

    public int GetTime() {
        return time;
    }
}
