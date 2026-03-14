package nextstep;

import java.util.List;
import java.util.Objects;

public abstract class Gamer {
    protected double betAmount;
    protected State state = new Started(new Cards());

    abstract public String getName();

    abstract public List<String> getCardNameInit();

    public void addCard(List<Card> card) {
        this.state = state.draw(card);
    }

    public List<String> getCardName() {
        return state.getCardName();
    }

    public boolean isBust() {
        return state instanceof Bust;
    }

    public boolean isFinished() {
        return state instanceof Finished;
    }

    public int sumScore() {
        return state.sumScore();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Gamer gamer = (Gamer) object;
        return Double.compare(betAmount, gamer.betAmount) == 0 && Objects.equals(state, gamer.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(betAmount, state);
    }
}
