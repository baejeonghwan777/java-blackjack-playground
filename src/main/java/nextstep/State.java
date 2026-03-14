package nextstep;

import java.util.List;

public interface State {
    State draw(List<Card> card);
    State stay();
    Cards cards();
    double profit(double betAmount, Dealer dealer);
    List<String> getCardName();
    List<String> getCardNameInit(int number);
    int sumScore();
}
