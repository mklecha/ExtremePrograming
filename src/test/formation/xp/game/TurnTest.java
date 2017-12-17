package formation.xp.game;

import formation.xp.cards.Deck;
import formation.xp.player.Player;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

public class TurnTest extends TestCase {

    public void testMaxStake() {
        Player player = new Player("");
        player.setMoney(100);
        Turn turn = new Turn(new Deck(), new ArrayList<>(Collections.singletonList(player)));

        assertEquals(0, turn.getMaxStake());

        player.bet(turn, 20);
        assertEquals(20, turn.getMaxStake());

        player.bet(turn, 10);
        assertEquals(20, turn.getMaxStake());

        player.bet(turn, 50);
        assertEquals(50, turn.getMaxStake());
    }
}
