package formation.xp;

import formation.xp.game.Game;
import formation.xp.player.AIPlayer;
import formation.xp.player.HumanPlayer;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        addPlayers(game);

        game.run();
    }

    static void addPlayers(Game game) {
        game.addPlayer(new HumanPlayer("P1"));
        game.addPlayer(new AIPlayer("AI - 1"));
        game.addPlayer(new AIPlayer("AI - 2"));

        game.getPlayers().forEach(p -> p.setMoney(1000));
    }
}
