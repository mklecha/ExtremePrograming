package formation.xp.cards;

public enum CardColor {
    DIAMOND('D'), HEART('H'), SPADE('S'), CLUB('C');

    private final char c;

    CardColor(char c) {
        this.c = c;
    }

    public char getValue() {
        return c;

    }
}
