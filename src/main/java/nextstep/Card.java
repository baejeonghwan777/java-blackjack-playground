package nextstep;

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
        return suit.getName() + rank.getScore();
    }
}
