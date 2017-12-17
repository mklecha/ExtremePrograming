package formation.xp.player;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void seeCards() {
        System.out.println("Your cards:");
        super.getCards().forEach(System.out::println);
    }
}
