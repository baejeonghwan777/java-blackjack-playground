package nextstep;

public class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double betAmount, Dealer dealer) {
        if(dealer.isBust()) return betAmount * earningRate();
        if(cards.sumScore() > dealer.sumScore()) return betAmount * earningRate();
        if(cards.sumScore() < dealer.sumScore()) return -betAmount;
        return 0;
    }
}
