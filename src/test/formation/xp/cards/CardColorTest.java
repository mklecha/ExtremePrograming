package formation.xp.cards;

import junit.framework.TestCase;

public class CardColorTest extends TestCase {

    public void testValues() {
        assertEquals('C', CardColor.CLUB.getValue());
        assertEquals('D', CardColor.DIAMOND.getValue());
        assertEquals('S', CardColor.SPADE.getValue());
        assertEquals('H', CardColor.HEART.getValue());

        CardColor color = CardColor.HEART;

        assertEquals(color, CardColor.HEART);
        assertNotSame(color, CardColor.DIAMOND);
    }
}
