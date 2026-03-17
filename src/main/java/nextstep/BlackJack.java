package nextstep;

public class BlackJack extends Finished {
    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double betAmount, Dealer dealer) {
        return betAmount * earningRate();
    }

    @Override
    public double earningRate() {
        return 1.5;
    }
}
