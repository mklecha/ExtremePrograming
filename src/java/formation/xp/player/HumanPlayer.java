package formation.xp.player;

import formation.xp.cards.Card;

import java.util.List;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

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
        System.out.print("Cards on the table: ");
        commonCards.forEach(c -> System.out.print(c + " "));
        System.out.println();
    }
}
