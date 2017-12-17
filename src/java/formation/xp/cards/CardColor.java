package formation.xp.cards;

public enum CardColor {
    DIAMOND('♢'), HEART('♡'), SPADE('♤'), CLUB('♧');

    private final char c;

    CardColor(char c) {
        this.c = c;
    }

    public char getValue() {
        return c;

    }
}
