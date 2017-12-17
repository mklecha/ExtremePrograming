package formation.xp.cards;

import formation.xp.exceptions.WrongCardNumberException;
import junit.framework.TestCase;

public class CardTest extends TestCase {

    public void testConstructor() {

        try {
            Card c1 = new Card(1, CardColor.HEART);
        } catch (WrongCardNumberException e) {
            fail("unexpected WrongCardNumberException");
        }

        try {
            Card c1 = new Card(13, CardColor.HEART);
        } catch (WrongCardNumberException e) {
            fail("unexpected WrongCardNumberException");
        }

        try {
            Card c1 = new Card(0, CardColor.HEART);
            fail("WrongCardNumberException expected");
        } catch (WrongCardNumberException ignored) {
        }

        try {
            Card c1 = new Card(14, CardColor.HEART);
            fail("WrongCardNumberException expected");
        } catch (WrongCardNumberException ignored) {
        }
    }

    public void testIntConstructor() {
        try {
            Card c1 = new Card(-1);
            fail("WrongCardNumberException expected");
        } catch (WrongCardNumberException ignored) {
        }

        try {
            Card c1 = new Card(52);
            fail("WrongCardNumberException expected");
        } catch (WrongCardNumberException ignored) {
        }


        Card c = new Card(0);
        assertEquals(new Card(1, CardColor.HEART), c);
        c = new Card(5);
        assertEquals(new Card(6, CardColor.HEART), c);


        c = new Card(13);
        assertEquals(new Card(1, CardColor.DIAMOND), c);
        c = new Card(21);
        assertEquals(new Card(9, CardColor.DIAMOND), c);


        c = new Card(26);
        assertEquals(new Card(1, CardColor.CLUB), c);
        c = new Card(36);
        assertEquals(new Card(11, CardColor.CLUB), c);

        c = new Card(39);
        assertEquals(new Card(1, CardColor.SPADE), c);
        c = new Card(51);
        assertEquals(new Card(13, CardColor.SPADE), c);
    }


    public void testEquals() {
        Card c1 = new Card(1, CardColor.HEART);
        Card c2 = new Card(1, CardColor.HEART);

        assertEquals(c1, c2);
    }

    public void testStringValue() {
        Card c = new Card(2, CardColor.HEART);
        assertEquals("H2", c.getStringValue());

        c = new Card(2, CardColor.DIAMOND);
        assertEquals("D2", c.getStringValue());

        c = new Card(2, CardColor.SPADE);
        assertEquals("S2", c.getStringValue());

        c = new Card(2, CardColor.CLUB);
        assertEquals("C2", c.getStringValue());

        c = new Card(7, CardColor.HEART);
        assertEquals("H7", c.getStringValue());

        c = new Card(8, CardColor.DIAMOND);
        assertEquals("D8", c.getStringValue());

        c = new Card(9, CardColor.SPADE);
        assertEquals("S9", c.getStringValue());

        c = new Card(10, CardColor.CLUB);
        assertEquals("C10", c.getStringValue());

        c = new Card(1, CardColor.HEART);
        assertEquals("H A", c.getStringValue());

        c = new Card(11, CardColor.DIAMOND);
        assertEquals("D J", c.getStringValue());

        c = new Card(12, CardColor.SPADE);
        assertEquals("S Q", c.getStringValue());

        c = new Card(13, CardColor.CLUB);
        assertEquals("C K", c.getStringValue());
    }

}
