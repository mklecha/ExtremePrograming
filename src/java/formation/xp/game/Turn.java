package formation.xp.game;

import formation.xp.cards.Deck;
import formation.xp.player.Player;

import java.util.List;

public class Turn {

    private Deck deck;
    private List<Player> players;

    public Turn(Deck deck, List<Player> players) {
        this.deck = deck;
        this.players = players;
    }

    private void giveCards() {
        for (int i = 0; i < 2; i++) {
            players.forEach(p -> p.addCard(deck.getCard()));
        }
    }

    public void start() {
        this.giveCards();
    }
}
