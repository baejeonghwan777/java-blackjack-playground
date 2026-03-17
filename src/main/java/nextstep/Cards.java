package nextstep;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {
    private final List<Card> cardList = new ArrayList<>();

    public List<String> getCardString() {
        List<String> cardString = new ArrayList<>();
        for (Card card : cardList) {
            cardString.add(card.toString());
        }
        return cardString;
    }

    public List<String> getCardStringInit(int number) {
        List<String> cardString = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            cardString.add(cardList.get(i).toString());
        }
        return cardString;
    }

    public int sumScore() {
        int sum = 0, aceCount = 0;
        for (Card card : cardList) {
            sum += card.getScore();
            if(card.isAce()) aceCount++;
        }
        while (aceCount > 0 && (sum + 10 <= 21)) {
            sum += 10;
            aceCount--;
        }
        return sum;
    }

    public boolean isBlackJack() {
        return cardList.size() == 2 && sumScore() == 21;
    }

    public void addCard(List<Card> card) {
        cardList.addAll(card);
    }

    public boolean isBust() {
        return sumScore() > 21;
    }

    public boolean isStay() {
        return sumScore() >= 21;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Cards cards = (Cards) object;
        return Objects.equals(cardList, cards.cardList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cardList);
    }
}
