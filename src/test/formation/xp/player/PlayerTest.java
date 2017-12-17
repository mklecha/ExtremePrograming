package formation.xp.player;

import formation.xp.cards.Deck;
import formation.xp.exceptions.NotEnoughMoneyException;
import formation.xp.game.Turn;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerTest extends TestCase {

    public void testConstructor() {
        String testName = "test";
        Player p = new Player(testName);

        assertEquals(testName, p.getName());
        assertTrue(p.getCards().isEmpty());
    }

    public void testBet() {
        Player player = new Player("");
        player.setMoney(100);
        Turn turn = new Turn(new Deck(), new ArrayList<>(Collections.singletonList(player)));

        player.bet(turn, 50);

        assertEquals(50, player.getMoney());
        assertEquals(50, turn.getMoneyInStake());

        player.bet(turn, 20);

        assertEquals(30, player.getMoney());
        assertEquals(70, turn.getMoneyInStake());
    }

    public void testTooMuchBet() {
        Player player = new Player("");
        player.setMoney(100);
        Turn turn = new Turn(new Deck(), new ArrayList<>(Collections.singletonList(player)));

        try {
            player.bet(turn, 150);
            fail("NotEnoughMoneyException expected");
        } catch (NotEnoughMoneyException ignored) {
        }
    }

    public void testCall() {
        Player player = new Player("");
        player.setMoney(100);
        Turn turn = new Turn(new Deck(), new ArrayList<>(Collections.singletonList(player)));

        player.call(turn);
        assertEquals(100, player.getMoney());

        player.bet(turn, 20);
        player.call(turn);

        assertEquals(60, player.getMoney());
    }

    public void testAllIn() {
        Player player = new Player("");
        player.setMoney(100);
        Turn turn = new Turn(new Deck(), new ArrayList<>(Collections.singletonList(player)));

        player.allIn(turn);

        assertEquals(0, player.getMoney());
    }

    public void testRaise() {
        Player player = new Player("");
        player.setMoney(100);
        Turn turn = new Turn(new Deck(), new ArrayList<>(Collections.singletonList(player)));

        player.bet(turn, 10);
        assertEquals(90, player.getMoney());

        player.raise(turn, 10);
        assertEquals(70, player.getMoney());

        try {
            player.raise(turn, 60);
            fail("NotEnoughMoneyException expected");
        } catch (NotEnoughMoneyException ignored) {
        }
    }
}
