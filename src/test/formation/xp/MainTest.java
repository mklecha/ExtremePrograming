package formation.xp;

import formation.xp.game.Game;

import junit.framework.TestCase;

public class MainTest extends TestCase {

    public void testAddPlayers() {
        String p1 = "p1", p2 = "p2";
        Game g = new Game();

        Main.addPlayers(g);

        assertFalse(g.getPlayers().isEmpty());
        g.getPlayers().forEach(s -> assertTrue(s.getName().equals(p1) || s.getName().equals(p2)));

    }
}
