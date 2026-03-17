package nextstep;

import java.util.List;

public class Control {
    private final Gamers gamers = new Gamers();
    private final CardDeck deck = new CardDeck();

    public void run() {
        ready(gamers);
        init(gamers, gamers.getGamerList());
        controlPlayer(gamers.getPlayerList());
        controlDealer(gamers.getDealer());
        checkSum(gamers.getGamerList());
        result(gamers.getPlayerList(), gamers.getDealer());
    }

    public void ready(Gamers gamers) {
        List<String> name = InputView.inputName();
        for (String string : name) {
            int money = InputView.inputMoney(string);
            gamers.addPlayer(string, money);
        }
        gamers.setPlayer();
    }

    public void init(Gamers gamers, List<Gamer> gamerList) {
        List<String> names = gamers.getPlayerName();
        ResultView.printInitInfo(names);
        for (Gamer gamer : gamerList) {
            drawCard(gamer, 2);
            viewCardInit(gamer);
        }
    }

    public void controlPlayer(List<Player> playerList) {
        for (Player player : playerList) {
            playerTurn(player);
        }
    }

    public void controlDealer(Dealer dealer) {
        int drawCount = 0;
        while(dealer.extraDraw()) {
            drawCard(dealer, 1);
            drawCount++;
        }
        if(drawCount != 0) ResultView.printDrawInfo(drawCount);
    }

    public void checkSum(List<Gamer> gamerList) {
        for (Gamer gamer : gamerList) {
            List<String> cardString = gamer.getCardName();
            ResultView.printSum(gamer.getName(), cardString, gamer.sumScore());
        }
    }

    public double result(List<Player> playerList, Dealer dealer) {
        double sumAmount = 0;
        for (Player player : playerList) {
            sumAmount -= player.getProfit(dealer);
        }
        organizeResult(playerList, dealer, sumAmount);
        return sumAmount;
    }

    public void organizeResult(List<Player> playerList, Dealer dealer, double sumAmount) {
        ResultView.printResult(dealer.getName(), sumAmount);
        for (Player player : playerList) {
            ResultView.printResult(player.getName(), player.getProfit(dealer));
        }
    }

    public void drawCard(Gamer gamer, int number) {
        List<Card> cardList;
        cardList = deck.draw(number);
        gamer.addCard(cardList);
    }

    private void viewCardInit(Gamer gamer) {
        List<String> cardString = gamer.getCardNameInit();
        ResultView.printCard(gamer.getName(), cardString);
    }

    private void viewCard(Gamer gamer) {
        List<String> cardString = gamer.getCardName();
        ResultView.printCard(gamer.getName(), cardString);
    }

    private void playerTurn(Player player) {
        while (!player.isFinished() && InputView.selectAddCard(player.getName())) {
            drawCard(player, 1);
            viewCard(player);
        }
        if (!player.isFinished()) player.setStay();
    }
}
