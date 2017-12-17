package formation.xp.player;

import formation.xp.cards.Card;
import formation.xp.exceptions.NotEnoughMoneyException;
import formation.xp.game.Turn;

import java.util.List;
import java.util.Random;

public class AIPlayer extends Player {

    private Random r = new Random();

    public AIPlayer(String name) {
        super(name);
    }

    //region see
    @Override
    public void seeCards() {
    }

    @Override
    public void seeMyMoney() {
    }

    @Override
    public void seeMyActualBet() {
    }

    @Override
    public void seeMaxBet(Turn turn) {
    }

    @Override
    public void seeCommonCards(List<Card> commonCards) {
    }
    //endregion

    @Override
    public void takeAction(Turn turn) {
        System.out.print("Player " + super.getName() + " ");

        int actualBet = 0;

        if (r.nextInt(100) == 0) {
            super.allIn(turn);
            System.out.println("goes AllIn! He placed bet for " + (getTurnBet() - actualBet) + " (" + getTurnBet() + " in total)");
            return;
        }

        try {
            switch (r.nextInt(5)) {
                case 0:
                    if (turn.getMaxStake() == getTurnBet()) {
                        super.call(turn);
                    }
                    pass(turn);
                    System.out.println("passes.");
                    break;
                case 1:
                    super.callAndRaise(turn, 10);
                    System.out.println("raises the stack by 10. He placed bet for " + (getTurnBet() - actualBet) + " (" + getTurnBet() + " in total)");
                    break;
                default:
                    super.call(turn);
                    System.out.println("calls. He placed bet for " + (getTurnBet() - actualBet) + " (" + getTurnBet() + " in total)");
                    break;
            }
        } catch (NotEnoughMoneyException e) {
            pass(turn);
            System.out.println("passes.");
        }
    }

    @Override
    public void win(int money) {
        super.win(money);
        System.out.println("Player " + getName() + " won " + money + "€");
    }
}
