package nextstep;

import java.util.Objects;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getScore() {
        return rank.getScore();
    }

    public boolean isAce() {
        return rank.isAce();
    }

    @Override
    public String toString() {
        return suit.getName() + rank.getIdentification();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Card card = (Card) object;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}
