package formation.xp.game;

import formation.xp.cards.Card;
import formation.xp.cards.Deck;
import formation.xp.exceptions.WrongCardAmountToShowException;
import formation.xp.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck;
    private List<Player> players;
    private List<Card> commonCards;

    private int money;

    public Game() {
        players = new ArrayList<>();
        commonCards = new ArrayList<>();
        this.deck = new Deck();
    }

    //region setters getters
    public List<Player> getPlayers() {
        return players;
    }

    public int getMoney() {
        return money;
    }

    List<Card> getCommonCards() {
        return commonCards;
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

        runTurn(new Turn(players));

        addCommonCard(3);

    }

    void runTurn(Turn turn) {
        turn.run(commonCards);
        money += turn.getMoneyInStake();

    }

    void addCommonCard(int cardAmount) {
        if (cardAmount != 3 && cardAmount != 1) {
            throw new WrongCardAmountToShowException();
        }
        for (int i = 0; i < cardAmount; i++) {
            commonCards.add(deck.getCard());
        }
    }
}
