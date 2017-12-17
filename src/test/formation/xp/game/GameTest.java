package formation.xp.game;

import formation.xp.player.Player;
import junit.framework.TestCase;
import org.junit.Test;

public class GameTest extends TestCase {

    public void testAddingPlayer() {
        Game g = new Game();

        Player p = new Player("");
        g.addPlayer(p);

        assertTrue(g.getPlayers().contains(p));
    }
}
