package nextstep;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private List<Card> cardList;
    private int score;
    private double money;

    public void addCard(Card card) {
        cardList.add(card);
    }

    public List<String> printCard() {
        List<String> cardString = new ArrayList<>();
        for (Card card : cardList) {
            cardString.add(card.toString());
        }
        return cardString;
    }

    public void checkBlackJack() {
        if(isBlackJack()) money = money * 1.5;
    }

    public boolean isBlackJack() {
        return cardList.size() == 2 && sumScore() == 21;
    }

    public double checkBust() {
        if(isBust()) {
            double lose = money;
            money = 0;
            return lose;
        }
        return 0;
    }

    public boolean isBust() {
        return sumScore() > 21;
    }

    public int sumScore() {
        int sum = 0;
        int aceCount = 0;
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
}
