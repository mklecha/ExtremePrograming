package formation.xp.player;

import formation.xp.cards.Card;
import formation.xp.exceptions.NotEnoughMoneyException;
import formation.xp.game.Turn;

import java.util.LinkedList;
import java.util.List;

public abstract class Player {
    private String name;
    private List<Card> cards;

    private int money;
    private int turnBet;

    public Player(String name) {
        this.cards = new LinkedList<>();
        this.name = name;
        this.turnBet = 0;
    }

    //region setters getters
    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public int getTurnBet() {
        return turnBet;
    }
    //endregion

    public void addCard(Card card) {
        cards.add(card);
    }

    void bet(Turn turn, int bet) {
        if (bet > money) {
            throw new NotEnoughMoneyException();
        }
        this.money -= bet;
        turn.placeBet(bet);
        this.turnBet += bet;
    }

    public void call(Turn turn) {
        bet(turn, turn.getMaxStake());
    }

    public void allIn(Turn turn) {
        bet(turn, money);
    }

    public void raise(Turn turn, int money) {
        bet(turn, turn.getMaxStake() + money);
    }

    public void pass(Turn turn) {
        turn.removePlayer(this);
    }

    public void resetTurnBet() {
        this.turnBet = 0;
    }

    public boolean isBroke() {
        return money <= 0;
    }

    public abstract void seeCards();

    public abstract void seeMyMoney();

    public abstract void seeCommonCards(List<Card> commonCards);
}
