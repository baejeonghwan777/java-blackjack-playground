package nextstep;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double betAmount, Dealer dealer) {
        return betAmount * earningRate();
    }

    @Override
    public double earningRate() {
        return -1;
    }
}
