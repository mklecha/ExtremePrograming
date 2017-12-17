package formation.xp.player;

import formation.xp.cards.Card;
import formation.xp.game.Turn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    //region see
    @Override
    public void seeCards() {
        System.out.print("Your cards: ");
        super.getCards().forEach(c -> System.out.print(c + " "));
        System.out.println();
    }

    @Override
    public void seeMyMoney() {
        System.out.println("My money: " + super.getMoney());
    }

    @Override
    public void seeCommonCards(List<Card> commonCards) {
        System.out.print("\nCards on the table: ");
        commonCards.forEach(c -> System.out.print(c + " "));
        System.out.print("\t\t");
    }

    @Override
    public void seeMyActualBet() {
        System.out.print("My actual bet is: " + super.getTurnBet());
        System.out.print("\t\t");
    }

    @Override
    public void seeMaxBet(Turn turn) {
        System.out.println("Max bet is: " + turn.getMaxStake());
    }
    //endregion

    @Override
    public void takeAction(Turn turn) {
        System.out.println("What do you want to do? You can:\n" +
                "\t(c) - Call or wait\n" +
                "\t(r) - Raise the stake\n" +
                "\t(a) - Go All In!\n" +
                "\t(p) - pass\n");

        int actualBet = getTurnBet();

        boolean repeat = true;
        while (repeat) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                switch (br.readLine()) {
                    case "a":
                        super.allIn(turn);
                        repeat = false;
                        System.out.println("You placed bet for " + (getTurnBet() - actualBet) + " (" + getTurnBet() + " in total)");
                        break;
                    case "c":
                        super.call(turn);
                        repeat = false;
                        System.out.println("You placed bet for " + (getTurnBet() - actualBet) + " (" + getTurnBet() + " in total)");
                        break;
                    case "r":
                        getRaiseArgument(br, turn);
                        repeat = false;
                        System.out.println("You placed bet for " + (getTurnBet() - actualBet) + " (" + getTurnBet() + " in total)");
                        break;
                    case "p":
                        pass(turn);
                        repeat = false;
                        break;
                    default:
                        System.out.println("Wrong answer!");
                        repeat = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getRaiseArgument(BufferedReader br, Turn turn) throws IOException {
        System.out.println("How much money over the stack you want to add?");
        boolean repeat = true;
        while (repeat) {

            repeat = false;
            try {
                int money = Integer.parseInt(br.readLine());

                callAndRaise(turn, money);
            } catch (IllegalArgumentException e) {
                repeat = true;
                System.out.println("Wrong amount of money input. You have " + super.getMoney());
            }
        }
    }

    @Override
    public void win(int money) {
        super.win(money);
        System.out.println("Congratulations! You won " + money + "€");
    }
}
