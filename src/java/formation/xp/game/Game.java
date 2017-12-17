package formation.xp.game;

import formation.xp.cards.Deck;
import formation.xp.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck;
    private List<Player> players;

    public Game() {
        players = new ArrayList<>();
        this.deck = new Deck();
    }

    //region setters getters
    public List<Player> getPlayers() {
        return players;
    }
    //endregion

    private void giveCards() {
        for (int i = 0; i < 2; i++) {
            players.forEach(p -> p.addCard(deck.getCard()));
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void run() {
        this.deck.shuffle();
        this.giveCards();

        Turn t1 = new Turn(players);
        t1.run();

    }
}
