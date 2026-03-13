package nextstep;

import java.util.ArrayList;
import java.util.List;

public class Player extends Gamer{
    private final String name;

    public Player(String name, int betAmount) { // 유효성 검사 필요
        this.name = name;
        if(betAmount >= 0) this.betAmount = betAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> printCardInit() {
        List<String> cardString = new ArrayList<>();
        cardString.add(cardList.get(0).toString());
        cardString.add(cardList.get(1).toString());
        return cardString;
    }
}
