package formation.xp.game;

import formation.xp.player.Player;

import java.util.List;

public class Turn {

    private List<Player> players;
    private int moneyInStake;
    private int maxStake;

    public Turn(List<Player> players) {
        this.players = players;
        players.removeIf(Player::isBroke);
        players.forEach(Player::resetTurnBet);
    }

    //region getters setters
    public int getMoneyInStake() {
        return moneyInStake;
    }

    public int getMaxStake() {
        return maxStake;
    }
    //endregion

    public void placeBet(int money) {
        maxStake = Math.max(maxStake, money);
        moneyInStake += money;
    }

    public boolean isPlayerPlaying(Player player) {
        return players.contains(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void run() {
        players.forEach(player -> {
            player.seeCards();
            player.seeMyMoney();
        });
    }
}
