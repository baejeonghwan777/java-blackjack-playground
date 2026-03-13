package nextstep;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Gamer {
    protected double betAmount;
    protected List<Card> cardList = new ArrayList<>();

    abstract public String getName();

    abstract public List<String> printCardInit();

    public void addCard(List<Card> card) {
        cardList.addAll(card);
    }

    public List<String> printCard() {
        List<String> cardString = new ArrayList<>();
        for (Card card : cardList) {
            cardString.add(card.toString());
        }
        return cardString;
    }

    public double getAmount() {
        return betAmount;
    }

    public void checkBlackJack() {
        if(isBlackJack()) betAmount = betAmount * 1.5;
    }

    public boolean isBlackJack() {
        return cardList.size() == 2 && sumScore() == 21;
    }

    public boolean isBust() {
        return sumScore() > 21;
    }

    public boolean isStay() {
        return sumScore() >= 21;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Gamer gamer = (Gamer) object;
        return Double.compare(betAmount, gamer.betAmount) == 0 && Objects.equals(cardList, gamer.cardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(betAmount, cardList);
    }
}
