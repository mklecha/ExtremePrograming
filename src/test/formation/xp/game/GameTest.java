package formation.xp.game;

import formation.xp.cards.Card;
import formation.xp.player.AIPlayer;
import formation.xp.player.Player;
import junit.framework.TestCase;

import java.util.HashSet;

public class GameTest extends TestCase {

    public void testAddingPlayer() {
        Game g = new Game();

        Player p = new AIPlayer("");
        g.addPlayer(p);

        assertTrue(g.getPlayers().contains(p));
    }

    public void testPlayersHaveCards() {
        Game g = new Game();

        for (int i = 0; i < 6; i++) {
            Player player = new AIPlayer("");
            player.setMoney(100);
            g.addPlayer(player);
        }

        g.run();

        g.getPlayers().forEach(p -> assertEquals(2, p.getCards().size()));

        HashSet<Card> cards = new HashSet<>();
        g.getPlayers().forEach(p -> cards.addAll(p.getCards()));

        assertEquals(12, cards.size());
    }

    public void testMoneyAfterTurn() {
        Game g = new Game();
        AIPlayer p = new AIPlayer("");
        p.setMoney(100);

        Turn t1 = new Turn(g.getPlayers());
        p.raise(t1, 20);

        assertEquals(20, t1.getMoneyInStake());
        assertEquals(80, p.getMoney());

        g.runTurn(t1);

        Turn t2 = new Turn(g.getPlayers());

        p.raise(t2, 20);

        assertEquals(20, t2.getMoneyInStake());
        assertEquals(60, p.getMoney());

        g.runTurn(t2);

        assertEquals(40, g.getMoney());

    }
}
