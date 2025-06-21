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
        Clock clock0 = new Clock(126);
        Clock clock1 = new Clock(1401);

        clock0.startClock();
        
        try {
            Thread.sleep(1001);
        } catch (InterruptedException e) {
        }

        clock0.stopClock();
        assertTrue(clock0.getTime() - 125 <= 1);
        int currentTime = clock0.getTime();

        clock1.startClock();
        try {
            Thread.sleep(5001);
            clock1.stopClock();
            assertTrue(clock1.getTime() - 1396 <= 1);
        } catch (InterruptedException e) {}

        assertEquals(currentTime, clock0.getTime());
    }
}
