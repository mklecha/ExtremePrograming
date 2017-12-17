package formation.xp.game;

import formation.xp.player.Player;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

public class TurnTest extends TestCase {

    private Player player;
    private Turn turn;

    private void createTurn() {
        player = new Player("");
        player.setMoney(100);
        turn = new Turn(new ArrayList<>(Collections.singletonList(player)));
    }

    public void testMaxStake() {
        createTurn();

        assertEquals(0, turn.getMaxStake());

        player.raise(turn, 20);
        assertEquals(20, turn.getMaxStake());

        player.call(turn);
        assertEquals(20, turn.getMaxStake());

        player.raise(turn, 30);
        assertEquals(50, turn.getMaxStake());
    }

    public void testTurnBets() {
        createTurn();

        player.raise(turn, 10);

        assertEquals(10, player.getTurnBet());
        assertEquals(90, player.getMoney());

        new Turn(new ArrayList<>(Collections.singletonList(player)));
        assertEquals(0, player.getTurnBet());
    }
}
