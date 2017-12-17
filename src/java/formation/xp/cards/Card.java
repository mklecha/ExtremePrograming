package formation.xp.cards;

import formation.xp.exceptions.WrongCardNumberException;

import java.util.Objects;

public class Card {
    private int cardNumber;
    private CardColor cardColor;

    public Card(int cardNumber) {
        if (cardNumber < 0 || cardNumber > 51) {
            throw new WrongCardNumberException();
        }
        switch (cardNumber / 13) {
            case 0:
                cardColor = CardColor.HEART;
                break;
            case 1:
                cardColor = CardColor.DIAMOND;
                break;
            case 2:
                cardColor = CardColor.CLUB;
                break;
            case 3:
                cardColor = CardColor.SPADE;
                break;
        }
        this.cardNumber = (cardNumber % 13) + 1;
    }

    public Card(int cardNumber, CardColor cardColor) {
        if (cardNumber < 1 || cardNumber > 13) {
            throw new WrongCardNumberException();
        }
        this.cardNumber = cardNumber;
        this.cardColor = cardColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardNumber == card.cardNumber &&
                cardColor == card.cardColor;
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardNumber, cardColor);
    }

    public String getStringValue() {
        String result = "" + cardColor.getValue();
        if (cardNumber < 11 && cardNumber > 1) {
            result += cardNumber;
        } else {
            switch (cardNumber) {
                case 1:
                    result += 'A';
                    break;
                case 11:
                    result += 'J';
                    break;
                case 12:
                    result += 'Q';
                    break;
                case 13:
                    result += 'K';
                    break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return this.getStringValue();
    }
}
