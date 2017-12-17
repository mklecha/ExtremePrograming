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

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void run() {
        System.out.println("Game started");
        Turn t1 = new Turn(deck, players);

        t1.start();


    }
}
