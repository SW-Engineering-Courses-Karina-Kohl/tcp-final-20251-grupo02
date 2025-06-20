package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatchTest {
    private final int desiredTime = 300;

    @Test
    public void testNewMatch() {
        Match match = new Match(desiredTime);

        assertEquals('w', match.getWhitePlayer().getColor());
        assertEquals('b', match.getBlackPlayer().getColor());

        assertEquals('w', match.getCurrentTurnPlayer().getColor());
        
        match.nextTurn();
        assertEquals('b', match.getCurrentTurnPlayer().getColor());

        match.nextTurn();

        assertEquals('w', match.getCurrentTurnPlayer().getColor());
    }
}