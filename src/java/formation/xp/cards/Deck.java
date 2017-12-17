package formation.xp.cards;

import formation.xp.exceptions.NoMoreCardsException;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

    private final static short DECK_SIZE = 52;

    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<>();
        for (int i = 0; i < DECK_SIZE; i++) {
            cards.add(new Card(i));
        }
    }

    //region getters setters
    public List<Card> getCards() {
        return cards;
    }
    //endregion

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card getCard() {
        if (cards.isEmpty()) {
            throw new NoMoreCardsException();
        }
        return cards.pop();
    }
}
