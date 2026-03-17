package nextstep;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {
    private final Rank rank;
    private final Suit suit;

    private static final Map<String, Card> CACHE = new HashMap<>();

    static {
        for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                CACHE.put(suit.name() + rank.name(), new Card(suit, rank));
    }

    public static Card of(Suit suit, Rank rank) {
        return CACHE.get(suit.name() + rank.name());
    }

    private Card(Suit suit, Rank rank) {
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
