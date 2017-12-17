package formation.xp.game;

import formation.xp.cards.Card;
import formation.xp.player.Player;

import java.util.ArrayList;
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

    public Player getWinner() {
        if (players.size() == 1) {
            return this.players.get(0);
        }
        return null;
    }
    //endregion

    public void placeBet(int money, int playerTurnBet) {
        maxStake = Math.max(maxStake, playerTurnBet);
        moneyInStake += money;
    }

    public boolean isPlayerPlaying(Player player) {
        return players.contains(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void run(Turn turn, List<Card> commonCards) {
        do {
            List<Player> tempList = new ArrayList<>(players);
            tempList.forEach(player -> {
                player.seeCommonCards(commonCards);
                player.seeCards();
                player.seeMyMoney();
                player.seeMyActualBet();
                player.seeMaxBet(turn);
                player.takeAction(turn);
            });
        }
        while (!allPlayersHaveSameBet(players));

        System.out.println("END BETTING TOURN\n\n");
    }

    private boolean allPlayersHaveSameBet(List<Player> list) {
        if (list.isEmpty()) return true;
        int bet = list.get(0).getTurnBet();
        for (Player player : list) {
            if (player.getTurnBet() != bet) {
                return false;
            }
        }
        return true;
    }
}
