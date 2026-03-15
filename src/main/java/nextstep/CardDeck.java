package nextstep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cardList = new ArrayList<>();;

    public CardDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cardList.add(Card.of(suit, rank));
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
