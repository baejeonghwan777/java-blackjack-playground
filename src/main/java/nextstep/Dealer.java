package nextstep;

import java.util.List;

public class Dealer extends Gamer {
    public boolean extraDraw() {
        return sumScore() <= 16;
    }

    @Override
    public String getName() {
        return "딜러";
    }

    @Override
    public List<String> getCardNameInit() {
        return state.getCardNameInit(1);
    }


}
