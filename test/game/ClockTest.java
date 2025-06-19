package game;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ClockTest {
    @Test
    public void testFormatClock(){
        int initialTime = 126;
        Clock clock0 = new Clock(initialTime);
        Clock clock1 = new Clock(1401);

        assertEquals("02:06", clock0.formatTime());
        assertEquals("23:21", clock1.formatTime());
    }

    @Test
    public void testStartClock(){
        int acceptedErrorInMilliS = 1000;

        Clock clock0 = new Clock(126);
        Clock clock1 = new Clock(1401);

        clock0.startClock();
        
        try {
            Thread.sleep(1000 + acceptedErrorInMilliS);
            assertEquals("02:05", clock0.formatTime());
        } catch (InterruptedException e) {
            assertEquals("02:06", clock0.formatTime());
        }
        clock0.stopClock();

        try {
            Thread.sleep(acceptedErrorInMilliS);
        } catch (InterruptedException e) {}

        int currentTime = clock0.getTime();

        clock1.startClock();
        try {
            Thread.sleep(5000 + acceptedErrorInMilliS);
            assertEquals("23:16", clock1.formatTime());
        } catch (InterruptedException e) {}

        assertEquals(currentTime, clock0.getTime());
    }
}
