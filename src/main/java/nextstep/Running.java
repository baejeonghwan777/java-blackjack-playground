package nextstep;

public class Running extends Started {
    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double betAmount, Dealer dealer) {
        throw new IllegalArgumentException("프로그램 실행 상태에서 계산은 불가능합니다.");
    }
}
