package nextstep;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJack {
    Players players = new Players();
    Dealer dealer = new Dealer();
    Cards cards = new Cards();
    List<Player> playerList = players.getPlayerList();
    double recoverySum = 0;

    public void run() {
        ready();
        init();
        control();
        checkSum();
        result();
    }

    public void ready() {
        List<String> name = InputView.inputName();
        for (String string : name) {
            int money = InputView.inputMoney(string);
            players.addPlayer(name, money);
        }
    }

    public void init() {
        List<String> names = players.getPlayerName();
        OutputView.printInitInfo(names);
        drawCardDealer(dealer, 2);
        dealer.checkBlackJack();
        for (Player player : playerList) {
            drawCard(player, 2);
            player.checkBlackJack();
        }
    }

    public void control() {
        int drawCount = 0;
        for (Player player : playerList) {
            if(InputView.selectAddCard(player.getName())) drawCard(player, 1);
        }
        while(dealer.sumScore() <= 16) {
            drawCardDealer(dealer, 1);
            drawCount++;
        }
        OutputView.printDrawInfo(drawCount);
    }

    public void checkSum() { // 딜러도 추가해야하나 ㅅㅂ
        List<String> cardStringDealer = dealer.printCard();
        OutputView.printDealerSum(cardStringDealer, dealer.sumScore());
        recoverySum += dealer.checkBust();
        for (Player player : playerList) {
            List<String> cardString = player.printCard();
            OutputView.printMySum(player.getName(), cardString, player.sumScore());
            recoverySum += player.checkBust();
        }
    }

    public void result() { // 딜러 반영 안됨 수정 필요
        List<Player> gains = playerList.stream().filter(Player::isBust).collect(Collectors.toList());
        int size = gains.size();
        if(!dealer.isBust()) size++;
        double dividends = recoverySum / size;
        if(dealer.isBust()) OutputView.printDealerResult(dividends);
        if(!dealer.isBust()) OutputView.printDealerResult(dealer.checkBust() * -1);
        for(Player player : playerList) {
            if(!player.isBust()) {
                player.addMoney(dividends);
                OutputView.printMyResult(dividends);
            }
            if(player.isBust()) OutputView.printMyResult(player.checkBust() * -1);
        }
    }

    public void drawCard(Player player, int number) {
        Card card;
        for (int i = 0; i < number; i++) {
            card = cards.draw();
            player.addCard(card);
        }
        List<String> cardString = player.printCard();
        OutputView.printMyCard(player.getName(), cardString);
    }

    public void drawCardDealer(Dealer dealer, int number) {
        Card card;
        for (int i = 0; i < number; i++) {
            card = cards.draw();
            dealer.addCard(card);
        }
        List<String> cardString = dealer.printCard();
        OutputView.printDealerCard(cardString);
    }
}
