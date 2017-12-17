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
}