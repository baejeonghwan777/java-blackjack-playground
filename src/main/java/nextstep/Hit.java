package nextstep;

import java.util.List;

public class Hit extends Running {
    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(List<Card> cardList) {
        cards.addCard(cardList);
        if(cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
