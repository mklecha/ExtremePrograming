package formation.xp.game;

import formation.xp.cards.Card;
import formation.xp.cards.Deck;
import formation.xp.exceptions.WrongCardAmountToShowException;
import formation.xp.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    List<Card> getCommonCards() {
        return commonCards;
    }

    Deck getDeck() {
        return deck;
    }

    //endregion

    public void giveCards() {
        for (int i = 0; i < 2; i++) {
            players.forEach(p -> p.addCard(deck.getCard()));
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    private void getPrice(Player winner) {
        winner.win(this.money);
        this.money = 0;
    }

    void runTurn(Turn turn) {
        turn.run(turn, commonCards);
        money += turn.getMoneyInStake();

    }

    void addCommonCard(int cardAmount) {
        if (cardAmount != 5 && cardAmount != 1) {
            throw new WrongCardAmountToShowException();
        }
        for (int i = 0; i < cardAmount; i++) {
            commonCards.add(deck.getCard());
        }
    }

    public void run() {
        this.deck.shuffle();
        this.giveCards();

        Turn turn = new Turn(players);
        runTurn(turn);

        addCommonCard(5);

        turn = new Turn(players);
        if (turn.getWinner() != null) {
            getPrice(turn.getWinner());
            return;
        }
        runTurn(turn);

        HandsEvaluator handsEvaluator = new HandsEvaluator(players.stream().filter(p -> !p.isBroke()).collect(Collectors.toSet()));
        handsEvaluator.addCommonCards(commonCards);
        handsEvaluator.evaluate();
        List<Player> winners = handsEvaluator.getWinners();
        winners.forEach(this::getPrice);
    }

}
