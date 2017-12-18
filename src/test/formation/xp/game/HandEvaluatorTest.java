package formation.xp.game;

import formation.xp.cards.Card;
import formation.xp.player.AIPlayer;
import formation.xp.player.Player;
import junit.framework.TestCase;

import java.util.*;
import java.util.stream.Collectors;

public class HandEvaluatorTest extends TestCase {

    public void testCommonCards() {
        Card c1 = new Card(1), c2 = new Card(2);

        HandsEvaluator handsEvaluator = new HandsEvaluator(new HashSet<>());

        handsEvaluator.addCommonCards(new ArrayList<>(Arrays.asList(c1, c2)));

        assertTrue(handsEvaluator.getCommonCards().contains(c1));
        assertTrue(handsEvaluator.getCommonCards().contains(c2));
    }

    public void testStreamHandConstruction() {
        List<Player> players = new ArrayList<>();

        Player p1 = new AIPlayer("p1");
        p1.setMoney(100);
        players.add(p1);
        Player p2 = new AIPlayer("p2");
        p2.setMoney(100);
        players.add(p2);
        Player p3 = new AIPlayer("p3");
        p3.setMoney(0);
        players.add(p3);

        Set s = players.stream().filter(p -> !p.isBroke()).collect(Collectors.toSet());
        assertTrue(s.contains(p1));
        assertTrue(s.contains(p2));
        assertFalse(s.contains(p3));
    }

    public void testEvaluateWinner() {
        Player p1 = new AIPlayer("p1");
        p1.setMoney(100);
        p1.addCard(new Card(12));
        p1.addCard(new Card(11));

        Player p2 = new AIPlayer("p2");
        p2.setMoney(100);
        p2.addCard(new Card(5));
        p2.addCard(new Card(6));

        HandsEvaluator handsEvaluator = new HandsEvaluator(new HashSet<>(Arrays.asList(p1, p2)));
        handsEvaluator.addCommonCards(new ArrayList<>(Arrays.asList(new Card(1), new Card(2), new Card(3))));

        handsEvaluator.evaluate();
        assertEquals(1, handsEvaluator.getWinners().size());
        assertTrue(handsEvaluator.getWinners().contains(p1));
    }

    public void testEvaluateWinners() {
        Player p1 = new AIPlayer("p1");
        p1.setMoney(100);
        p1.addCard(new Card(12));
        p1.addCard(new Card(11));

        Player p2 = new AIPlayer("p2");
        p2.setMoney(100);
        p2.addCard(new Card(25));
        p2.addCard(new Card(24));

        HandsEvaluator handsEvaluator = new HandsEvaluator(new HashSet<>(Arrays.asList(p1, p2)));
        handsEvaluator.addCommonCards(new ArrayList<>(Arrays.asList(new Card(1), new Card(2), new Card(3))));

        handsEvaluator.evaluate();
        assertEquals(2, handsEvaluator.getWinners().size());
        assertTrue(handsEvaluator.getWinners().contains(p1));
        assertTrue(handsEvaluator.getWinners().contains(p2));
    }
}
