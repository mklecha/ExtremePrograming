package formation.xp.cards;

import junit.framework.TestCase;

public class CardColorTest extends TestCase {

    public void testValues() {
        assertEquals('♧', CardColor.CLUB.getValue());
        assertEquals('♢', CardColor.DIAMOND.getValue());
        assertEquals('♤', CardColor.SPADE.getValue());
        assertEquals('♡', CardColor.HEART.getValue());

        CardColor color = CardColor.HEART;

        assertEquals(color, CardColor.HEART);
        assertNotSame(color, CardColor.DIAMOND);
    }
}
