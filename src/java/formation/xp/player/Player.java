package formation.xp.player;

import formation.xp.cards.Card;
import formation.xp.exceptions.NotEnoughMoneyException;
import formation.xp.game.Turn;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> cards;

    private int money;

    public Player(String name) {
        this.cards = new LinkedList<>();
        this.name = name;
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
    //endregion

    public void addCard(Card card) {
        cards.add(card);
    }


    public void bet(Turn turn, int bet) {
        if (bet > money) {
            throw new NotEnoughMoneyException();
        }
        this.money -= bet;
        turn.placeBet(bet);
    }

    public void call(Turn turn) {
        bet(turn, turn.getMaxStake());
    }

    public void allIn(Turn turn) {
        bet(turn, money);
    }
}
