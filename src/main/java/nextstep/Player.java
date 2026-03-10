package nextstep;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private int score;
    private double money;
    private List<Card> cardList;
    private final int FIRST = 0;
    private final int SECOND = 1;


    public Player(String name, int money) { // 유효성 검사 필요
        this.name = name;
        this.money = money;
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    public void addMoney(double money) {
        this.money += money;
    }

    public List<String> printCard() {
        List<String> cardString = new ArrayList<>();
        for (Card card : cardList) {
            cardString.add(card.toString());
        }
        return cardString;
    }

    public String getName() {
        return name;
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
