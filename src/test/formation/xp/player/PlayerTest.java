package formation.xp.player;

import formation.xp.cards.Card;
import formation.xp.exceptions.NotEnoughMoneyException;
import formation.xp.game.Game;
import formation.xp.game.Turn;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerTest extends TestCase {

    private Player player;
    private Turn turn;

    private void createPlayer(int money) {
        player = new AIPlayer("");
        player.setMoney(money);
        turn = new Turn(new ArrayList<>(Collections.singletonList(player)));
    }

    public void testConstructor() {
        String testName = "test";
        Player p = new AIPlayer(testName);

        assertEquals(testName, p.getName());
        assertTrue(p.getCards().isEmpty());
    }

    public void testBet() {
        createPlayer(100);

        player.bet(turn, 50);

        assertEquals(50, player.getMoney());
        assertEquals(50, turn.getMoneyInStake());

        player.bet(turn, 20);

        assertEquals(30, player.getMoney());
        assertEquals(70, turn.getMoneyInStake());
    }

    public void testTooMuchBet() {
        createPlayer(100);

        try {
            player.bet(turn, 150);
            fail("NotEnoughMoneyException expected");
        } catch (NotEnoughMoneyException ignored) {
        }
    }

    public void testCall() {
        createPlayer(100);

        player.call(turn);
        assertEquals(100, player.getMoney());

        player.bet(turn, 20);
        player.call(turn);

        assertEquals(60, player.getMoney());
    }

    public void testAllIn() {
        createPlayer(100);

        player.allIn(turn);

        assertEquals(0, player.getMoney());
    }

    public void testRaise() {
        createPlayer(100);

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

    public void testPass() {
        createPlayer(100);

        player.pass(turn);

        assertEquals(100, player.getMoney());
        assertFalse(turn.isPlayerPlaying(player));
    }

    public void testTurnBet() {
        createPlayer(100);
        player.bet(turn, 10);
        player.raise(turn, 20);

        assertEquals(40, player.getTurnBet());
    }

    public void testGetMyCards() {
        createPlayer(100);
        Game g = new Game();
        g.addPlayer(player);
        g.run();

        List<Card> cards = player.getCards();
        assertEquals(2, cards.size());
        assertNotSame(cards.get(0), cards.get(1));
    }
}
