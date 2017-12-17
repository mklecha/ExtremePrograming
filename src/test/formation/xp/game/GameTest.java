package formation.xp.game;

import formation.xp.cards.Card;
import formation.xp.player.Player;
import junit.framework.TestCase;

import java.util.HashSet;

public class GameTest extends TestCase {

    public void testAddingPlayer() {
        Game g = new Game();

        Player p = new Player("");
        g.addPlayer(p);

        assertTrue(g.getPlayers().contains(p));
    }

    public void testPlayersHaveCards() {
        Game g = new Game();

        g.addPlayer(new Player("p1"));
        g.addPlayer(new Player("p2"));
        g.addPlayer(new Player("p3"));
        g.addPlayer(new Player("p4"));
        g.addPlayer(new Player("p5"));
        g.addPlayer(new Player("p6"));

        g.run();

        g.getPlayers().forEach(p -> assertEquals(2, p.getCards().size()));

        HashSet<Card> cards = new HashSet<>();
        g.getPlayers().forEach(p -> cards.addAll(p.getCards()));

        assertEquals(12, cards.size());
    }
}
