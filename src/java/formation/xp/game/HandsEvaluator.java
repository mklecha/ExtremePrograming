package formation.xp.game;

import formation.xp.cards.Card;
import formation.xp.player.Player;

import java.util.*;

public class HandsEvaluator {

    private List<Card> commonCards;
    private Set<Player> players;

    private Map<Player, Float> scores;


    public HandsEvaluator(Set<Player> players) {
        commonCards = new ArrayList<>();
        scores = new HashMap<>();
        this.players = players;
    }

    //region getters setter
    public List<Card> getCommonCards() {
        return commonCards;
    }
    //endregion

    public void addCommonCards(List<Card> commonCards) {
        this.commonCards.addAll(commonCards);
    }


    public void evaluate() {
        for (Player player : players) {
            List<Card> cards = new ArrayList<>(commonCards);
            cards.addAll(player.getCards());
            Collections.sort(cards);
            Collections.reverse(cards);

            float sum = 0f;

            for (int i = 0; i < 5; i++) {
                sum += cards.get(i).getCardNumber();
            }
            scores.put(player, sum);
        }
    }

    public List<Player> getWinners() {
        List<Player> result = new ArrayList<>();

        float max = 0f;
        for (Float score : scores.values()) {
            max = Math.max(max, score);
        }

        for (Map.Entry<Player, Float> e : scores.entrySet()) {
            if (e.getValue() == max) {
                result.add(e.getKey());
            }
        }
        return result;
    }

}
