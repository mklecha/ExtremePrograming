package formation.xp.game;

import formation.xp.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Player> players;

    public Game() {
        players = new ArrayList<>();
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
    }
}
