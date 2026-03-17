package nextstep;

import java.util.List;
import java.util.Objects;

public class Started implements State {
    protected Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(List<Card> cardList) {
        cards.addCard(cardList);
        if(cards.isBlackJack()) {
            return new BlackJack(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("프로그램 시작 상태에서 Stay를 외칠 수 없습니다.");
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public double profit(double betAmount, Dealer dealer) {
        throw new IllegalArgumentException("프로그램 시작 상태에서 계산은 불가능합니다.");
    }

    @Override
    public List<String> getCardName() {
        return cards.getCardString();
    }

    @Override
    public List<String> getCardNameInit(int number) {
        return cards.getCardStringInit(number);
    }

    @Override
    public int sumScore() {
        return cards.sumScore();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Started started = (Started) object;
        return Objects.equals(cards, started.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
