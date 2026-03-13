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

    public List<Card> draw(int number) {
        List<Card> drawCard = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            if(cardList.isEmpty()) break;
            drawCard.add(cardList.remove(0));
        }
        return drawCard;
    }
}
