package nextstep;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Gamer {
    public boolean extraDraw() {
        return sumScore() <= 16;
    }

    @Override
    public String getName() {
        return "딜러";
    }

    @Override
    public List<String> printCardInit() {
        List<String> cardString = new ArrayList<>();
        cardString.add(cardList.get(0).toString());
        return cardString;
    }


}
