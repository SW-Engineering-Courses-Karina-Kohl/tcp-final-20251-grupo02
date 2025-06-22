package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private final int desiredTime = 300;

    @Test
    public void testPlayerCreation(){
        Player white = new Player('w', desiredTime);

        assertEquals("05:00", white.getClock().formatTime());
        assertEquals('w', white.getColor());
        assertFalse(white.getCheckStatus());
    }
}
