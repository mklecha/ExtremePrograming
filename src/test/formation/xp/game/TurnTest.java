package formation.xp.game;

import formation.xp.player.AIPlayer;
import formation.xp.player.Player;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TurnTest extends TestCase {

    private Player player;
    private Turn turn;

    private void createTurn() {
        player = new AIPlayer("");
        player.setMoney(100);
        turn = new Turn(new ArrayList<>(Collections.singletonList(player)));
    }

    public void testMaxStake() {
        createTurn();

        assertEquals(0, turn.getMaxStake());

        player.callAndRaise(turn, 20);
        assertEquals(20, turn.getMaxStake());

        player.call(turn);
        assertEquals(20, turn.getMaxStake());

        Player p2 = new AIPlayer("");
        p2.setMoney(100);


        p2.callAndRaise(turn, 30);
        assertEquals(50, turn.getMaxStake());
    }

    public void testTurnBets() {
        createTurn();

        player.callAndRaise(turn, 10);

        assertEquals(10, player.getTurnBet());
        assertEquals(90, player.getMoney());

        new Turn(new ArrayList<>(Collections.singletonList(player)));
        assertEquals(0, player.getTurnBet());
    }

    public void testOnlyPlayersWithMoneyPlaying() {
        Player p1 = new AIPlayer("p1"), p2 = new AIPlayer("p2");
        p1.setMoney(100);
        p2.setMoney(0);

        Turn turn = new Turn(new ArrayList<>(Arrays.asList(p1, p2)));

        assertTrue(turn.isPlayerPlaying(p1));
        assertFalse(turn.isPlayerPlaying(p2));
    }

}
