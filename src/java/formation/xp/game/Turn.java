package formation.xp.game;

import formation.xp.cards.Deck;
import formation.xp.player.Player;

import java.util.List;

public class Turn {

    private Deck deck;
    private List<Player> players;
    private int moneyInStake;
    private int maxStake;

    public Turn(Deck deck, List<Player> players) {
        this.deck = deck;
        this.players = players;
    }

    //region getters setters
    public int getMoneyInStake() {
        return moneyInStake;
    }

    public int getMaxStake() {
        return maxStake;
    }
    //endregion

    private void giveCards() {
        for (int i = 0; i < 2; i++) {
            players.forEach(p -> p.addCard(deck.getCard()));
        }
    }

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

    public void start() {
        this.giveCards();
    }
}
