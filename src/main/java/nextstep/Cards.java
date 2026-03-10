package nextstep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    List<Card> cardList = new ArrayList<>();

    public Cards() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cardList.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cardList);
    }

    public Card draw() {
        if(cardList.isEmpty()) return null;
        return cardList.remove(0);
    }
}
