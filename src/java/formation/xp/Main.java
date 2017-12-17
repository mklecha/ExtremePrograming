package formation.xp;

import formation.xp.game.Game;
import formation.xp.player.Player;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        addPlayers(game);

        game.run();
    }

    static void addPlayers(Game game) {
        game.addPlayer(new Player("p1"));
        game.addPlayer(new Player("p2"));
    }
}
