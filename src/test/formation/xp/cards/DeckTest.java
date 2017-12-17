package formation.xp.cards;

import formation.xp.exceptions.NoMoreCardsException;
import junit.framework.TestCase;

import java.util.Collections;
import java.util.List;

public class DeckTest extends TestCase {

    public void testConstructor() {
        Deck d = new Deck();

        assertEquals(52, d.getCards().size());

        assertEquals(new Card(1, CardColor.HEART), d.getCards().get(0));

        List<Card> cards = d.getCards();
        Collections.reverse(cards);

        assertEquals(new Card(13, CardColor.SPADE), cards.get(0));
    }

    public void testShuffle() {
        Deck d = new Deck();

        Card firstCart = d.getCards().get(0);
        d.shuffle();
        assertEquals(52, d.getCards().size());

        assertNotSame(firstCart, d.getCards().get(0));
    }

    public void testGetCard() {
        Deck d = new Deck();

        assertEquals(d.getCards().get(d.getCards().size() - 1), d.getCard());

        assertEquals(51, d.getCards().size());

        try {
            for (int i = 0; i < 52; i++) {
                d.getCard();
            }
            fail("NoMoreCardsException expected");
        } catch (NoMoreCardsException ignored) {
        }
    }
}
