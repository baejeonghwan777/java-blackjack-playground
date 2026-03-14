package nextstep;

public class Finished extends Started {
    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State stay() {
        return super.stay();
    }

    @Override
    public Cards cards() {
        return super.cards();
    }

    @Override
    public int sumScore() {
        return super.sumScore();
    }

    @Override
    public double profit(double betAmount, Dealer dealer) {
        return super.profit(betAmount, dealer);
    }

    public double earningRate() {
        return 1;
    }
}
