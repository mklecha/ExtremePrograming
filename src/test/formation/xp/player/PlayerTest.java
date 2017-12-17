package formation.xp.player;

import junit.framework.TestCase;
import org.junit.Test;

public class PlayerTest extends TestCase {

    public void testConstructor() {
        String testName = "test";
        Player p = new Player(testName);

        assertEquals(testName, p.getName());
    }
}
