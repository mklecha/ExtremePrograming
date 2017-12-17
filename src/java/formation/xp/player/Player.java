package formation.xp.player;

import formation.xp.cards.Card;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> cards;

    public Player(String name) {
        this.cards = new LinkedList<>();
        this.name = name;
    }

    //region setters getters
    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
    //endregion

    public void addCard(Card card) {
        cards.add(card);
    }


}
