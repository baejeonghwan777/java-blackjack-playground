package nextstep;

import java.util.List;

public class Player extends Gamer {
    private final String name;

    public Player(String name, int betAmount) {
        this.name = name;
        if(betAmount >= 0) this.betAmount = betAmount;
        if(betAmount < 0) this.betAmount = 0;
    }

    public double getProfit(Dealer dealer) {
        return state.profit(betAmount, dealer);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getCardNameInit() {
        return state.getCardNameInit(2);
    }

    public void setStay() {
        this.state = state.stay();
    }
}
