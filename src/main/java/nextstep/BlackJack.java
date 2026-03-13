package nextstep;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJack {
    private final Gamers gamers = new Gamers();
    private final Cards cards = new Cards();
    private final Dealer dealer = gamers.getDealer();
    private final List<Gamer> gamerList = gamers.getGamerList();
    private final int LOSE_CONST = -1;

    public void run() {
        ready(gamers);
        init(gamers, gamerList);
        controlPlayer(gamers.getPlayerList());
        controlDealer(dealer);
        checkSum(gamerList);
        result(gamers.getPlayerList(), dealer);
    }

    public void ready(Gamers gamers) {
        List<String> name = InputView.inputName();
        gamers.addDealer();
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
            gamer.checkBlackJack();
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
        ResultView.printDrawInfo(drawCount);
    }

    public void checkSum(List<Gamer> gamerList) {
        for (Gamer gamer : gamerList) {
            List<String> cardString = gamer.printCard();
            ResultView.printSum(gamer.getName(), cardString, gamer.sumScore());
        }
    }

    public Map<String, List<Player>> checkWinner(List<Player> playerList, Dealer dealer) {
        return playerList.stream()
                .collect(Collectors.groupingBy(player -> {
                    if (player.isBust()) return "LOSE";
                    if (dealer.isBust()) return "WIN";
                    if (player.sumScore() > dealer.sumScore() || player.isBlackJack()) return "WIN";
                    if (player.sumScore() < dealer.sumScore() || dealer.isBlackJack()) return "LOSE";
                    return "PUSH";
                }));
    }

    public void result(List<Player> playerList, Dealer dealer) {
        Map<String, List<Player>> result = checkWinner(playerList, dealer);
        List<Player> winners = result.get("WIN");
        List<Player> pushers = result.getOrDefault("PUSH", List.of());
        List<Player> losers = result.get("LOSE");
        double moneySum = checkAmount(winners, losers);
        organizeResult(dealer, winners, pushers, losers, moneySum);
    }

    public double checkAmount(List<Player> winners, List<Player> losers) {
        double moneySum = 0;
        for (Player winner : winners) moneySum -= winner.getAmount();
        for (Player loser : losers) moneySum += loser.getAmount();
        return moneySum;
    }

    public void organizeResult(Dealer dealer, List<Player> winners, List<Player> pushers, List<Player> losers, double moneySum) {
        ResultView.printResult(dealer.getName(), moneySum);
        for (Player winner : winners) ResultView.printResult(winner.getName(), winner.getAmount());
        for (Player pusher : pushers) ResultView.printResult(pusher.getName(), 0);
        for (Player loser : losers) ResultView.printResult(loser.getName(), loser.getAmount() * LOSE_CONST);
    }

    public void drawCard(Gamer gamer, int number) {
        List<Card> cardList;
        cardList = cards.draw(number);
        gamer.addCard(cardList);
    }

    private void viewCardInit(Gamer gamer) {
        List<String> cardString = gamer.printCardInit();
        ResultView.printCard(gamer.getName(), cardString);
    }

    private void viewCard(Gamer gamer) {
        List<String> cardString = gamer.printCard();
        ResultView.printCard(gamer.getName(), cardString);
    }

    private void playerTurn(Gamer player) {
        while (!player.isStay() && InputView.selectAddCard(player.getName())) {
            drawCard(player, 1);
            viewCard(player);
        }
    }
}
