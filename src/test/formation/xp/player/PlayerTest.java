package formation.xp.player;

import formation.xp.cards.Card;
import formation.xp.exceptions.NotEnoughMoneyException;
import formation.xp.game.Game;
import formation.xp.game.Turn;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void testMoney() {
        createPlayer(100);
        assertEquals(100, player.getMoney());
    }


    public void testBet() {
        createPlayer(100);

        player.callAndRaise(turn, 50);

        assertEquals(50, player.getMoney());
        assertEquals(50, turn.getMoneyInStake());
    }

    public void testCall() {
        createPlayer(100);

        player.call(turn);
        assertEquals(100, player.getMoney());

        player.callAndRaise(turn, 20);
        player.call(turn);

        assertEquals(80, player.getMoney());
    }

    public void testBetAndCall() {
        createPlayer(100);
        Player player2 = new AIPlayer("");

        player2.setMoney(100);

        turn = new Turn(new ArrayList<>(Arrays.asList(player, player2)));

        player.callAndRaise(turn, 50);

        assertEquals(50, player.getMoney());
        assertEquals(50, turn.getMoneyInStake());

        player2.call(turn);

        assertEquals(50, player.getMoney());
        assertEquals(50, player2.getMoney());
        assertEquals(100, turn.getMoneyInStake());
    }


    public void testTooBigBet() {
        createPlayer(100);
        try {
            player.callAndRaise(turn, 150);
            fail("NotEnoughMoneyException expected");
        } catch (NotEnoughMoneyException ignored) {
        }
    }

    public void testAllIn() {
        createPlayer(100);
        player.allIn(turn);
        assertEquals(0, player.getMoney());

        assertEquals(100, turn.getMoneyInStake());
    }

    public void testCallAndRaise() {
        createPlayer(100);
        Player player2 = new AIPlayer("");

        player2.setMoney(100);

        turn = new Turn(new ArrayList<>(Arrays.asList(player, player2)));

        player.callAndRaise(turn, 10);
        assertEquals(90, player.getMoney());
        assertEquals(10, turn.getMoneyInStake());

        player2.callAndRaise(turn, 10);
        assertEquals(80, player2.getMoney());
        assertEquals(30, turn.getMoneyInStake());

        player.callAndRaise(turn, 10);
        assertEquals(70, player.getMoney());
        assertEquals(50, turn.getMoneyInStake());

        try {
            player.callAndRaise(turn, 100);
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
        player.callAndRaise(turn, 10);
        player.callAndRaise(turn, 20);

        assertEquals(30, player.getTurnBet());
    }

    public void testGetMyCards() {
        createPlayer(100);
        Game g = new Game();
        g.addPlayer(player);

        g.giveCards();

        List<Card> cards = player.getCards();
        assertEquals(2, cards.size());
        assertNotSame(cards.get(0), cards.get(1));
    }

    public void testComplexCase() {
        createPlayer(100);
        Player player2 = new AIPlayer("");
        player2.setMoney(100);
        turn = new Turn(new ArrayList<>(Arrays.asList(player, player2)));

        player.call(turn);
        assertEquals(100, player.getMoney());

        player2.callAndRaise(turn, 10);
        assertEquals(90, player2.getMoney());
        assertEquals(10, turn.getMoneyInStake());
        assertEquals(10, turn.getMaxStake());

        player.call(turn);
        assertEquals(90, player.getMoney());
        assertEquals(20, turn.getMoneyInStake());
        assertEquals(10, turn.getMaxStake());

        player2.callAndRaise(turn, 10);
        assertEquals(80, player2.getMoney());
        assertEquals(30, turn.getMoneyInStake());
        assertEquals(20, turn.getMaxStake());

        player.call(turn);
        assertEquals(80, player.getMoney());
        assertEquals(40, turn.getMoneyInStake());


//        p1 call(10)
//        p 10(20)
//        p call(10) !!!!

    }
}
