package nextstep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BlackJackTest {
    BlackJack blackJack;
    Gamers gamers;
    Cards cards;
    Dealer dealer;
    List<Player> playerList;
    List<Gamer> gamerList;

    @BeforeEach
    public void setUp() {
        blackJack = new BlackJack();
        gamers = new Gamers();
        cards = new Cards();
        dealer = new Dealer();
        playerList = new ArrayList<>();
        gamerList = new ArrayList<>();
    }

    @DisplayName("게이머 리스트 목록이 잘 출력되는지 확인한다.")
    @Test
    public void addTest() {
        List<Gamer> testList = new ArrayList<>();
        testList.add(dealer);
        testList.add(new Player("김길동", 20000));
        testList.add(new Player("배정환", 40000));

        gamers.addDealer();
        gamers.addPlayer("김길동", 20000);
        gamers.addPlayer("배정환", 40000);
        gamerList = gamers.getGamerList();

        assertThat(testList).isEqualTo(gamerList);
    }

    @DisplayName("드로우로 뽑힌 모든 카드의 경우가 겹치지 않는지 확인한다.")
    @Test
    public void drawTest() {
        Set<Card> test = new HashSet<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                test.add(new Card(suit, rank));
            }
        }

        Cards cards = new Cards();
        List<Card> cardList = cards.draw(test.size());
        Set<Card> result = new HashSet<>(cardList);

        assertThat(result).isEqualTo(test);
    }

    @DisplayName("드로우한 카드가 플레이어에게 제대로 반영이 되는지 확인한다.")
    @Test
    public void drawValidPlayerTest() {
        int expected = 2;

        Player player = new Player("배정환", 40000);
        blackJack.drawCard(player, expected);
        List<String> cardString = player.printCard();

        assertThat(cardString.size()).isEqualTo(expected);
    }

    @DisplayName("드로우한 카드가 딜러에게 제대로 반영이 되는지 확인한다.")
    @Test
    public void drawValidDealerTest() {
        int expected = 2;

        blackJack.drawCard(dealer, expected);
        List<String> cardString = dealer.printCard();

        assertThat(cardString.size()).isEqualTo(expected);
    }

    @DisplayName("딜러가 첫 턴에 두장의 카드를 뽑았을 때 한장만 카드만 출력되는지 확인한다.")
    @Test
    public void drawValidDealerPrintTest() {
        int expected = 1;

        blackJack.drawCard(dealer, expected);
        List<String> cardString = dealer.printCardInit();

        assertThat(cardString.size()).isEqualTo(expected);
    }


    @DisplayName("딜러가 카드 두장을 뽑고 16보다 합계 숫자가 낮은 경우 카드를 뽑는지 확인한다.")
    @Test
    public void extraDrawTest() {
        boolean expected = true;

        blackJack.controlDealer(dealer);
        boolean result = dealer.sumScore() >= 16;

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("에이스가 11점일 때 플레이어의 카드 점수 합계가 잘 반영되는지 확인한다.")
    @Test
    public void playerSumElevenTest() {
        int expected = 21;

        Player player = new Player("배정환", 40000);
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.SPADE, Rank.QUEEN));
        cardList.add(new Card(Suit.DIAMOND, Rank.ACE));
        player.addCard(cardList);
        int result = player.sumScore();

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("에이스가 1점일 때 플레이어의 카드 점수 합계가 잘 반영되는지 확인한다.")
    @Test
    public void playerSumOneTest() {
        int expected = 16;

        Player player = new Player("배정환", 40000);
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.SPADE, Rank.NINE));
        cardList.add(new Card(Suit.SPADE, Rank.SIX));
        cardList.add(new Card(Suit.DIAMOND, Rank.ACE));
        player.addCard(cardList);
        int result = player.sumScore();

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("현재 가지고 있는 카드가 전부 출력되는지 확인한다.")
    @Test
    public void printTest() {
        List<String> expected = new ArrayList<>();

        Player player = new Player("배정환", 40000);
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.SPADE, Rank.NINE));
        cardList.add(new Card(Suit.SPADE, Rank.SIX));
        cardList.add(new Card(Suit.DIAMOND, Rank.ACE));
        player.addCard(cardList);

        List<String> result = player.printCard();
        expected.add(cardList.get(0).toString());
        expected.add(cardList.get(1).toString());
        expected.add(cardList.get(2).toString());

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("딜러의 수익이 제대로 정산되었는지 확인한다.")
    @Test
    public void dealerAmountTest() {
        double expected = 10000;

        Player player1 = new Player("배정환", 40000);
        Player player2 = new Player("apple", 20000);
        Player player3 = new Player("banana", 70000);

        List<Player> winners = new ArrayList<>();
        List<Player> losers = new ArrayList<>();

        winners.add(player1);
        winners.add(player2);
        losers.add(player3);
        double result = blackJack.checkAmount(winners, losers);

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("딜러 점수와 비교하여 플레이어의 승패 여부가 제대로 판별되는지 확인한다.")
    @Test
    public void Test() {
        List<Player> expectedWin = new ArrayList<>();
        List<Player> expectedPush = new ArrayList<>();
        List<Player> expectedLose = new ArrayList<>();

        Dealer dealer = new Dealer();
        List<Card> cardListD = new ArrayList<>();
        cardListD.add(new Card(Suit.SPADE, Rank.NINE));
        cardListD.add(new Card(Suit.SPADE, Rank.SIX));
        cardListD.add(new Card(Suit.DIAMOND, Rank.ACE));
        dealer.addCard(cardListD);

        Player player1 = new Player("배정환", 40000);
        List<Card> cardList1 = new ArrayList<>();
        cardList1.add(new Card(Suit.HEART, Rank.NINE));
        cardList1.add(new Card(Suit.SPADE, Rank.SEVEN));
        cardList1.add(new Card(Suit.CLOVER, Rank.ACE));
        player1.addCard(cardList1);

        Player player2 = new Player("apple", 20000);
        List<Card> cardList2 = new ArrayList<>();
        cardList2.add(new Card(Suit.SPADE, Rank.EIGHT));
        cardList2.add(new Card(Suit.SPADE, Rank.THREE));
        cardList2.add(new Card(Suit.HEART, Rank.FIVE));
        player2.addCard(cardList2);

        Player player3 = new Player("banana", 30000);
        List<Card> cardList3 = new ArrayList<>();
        cardList3.add(new Card(Suit.DIAMOND, Rank.TWO));
        cardList3.add(new Card(Suit.CLOVER, Rank.SIX));
        cardList3.add(new Card(Suit.DIAMOND, Rank.FIVE));
        player3.addCard(cardList3);

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        expectedWin.add(player1);
        expectedPush.add(player2);
        expectedLose.add(player3);

        Map<String, List<Player>> result = blackJack.checkWinner(playerList, dealer);

        assertAll(
                () -> assertThat(result.get("WIN")).isEqualTo(expectedWin),
                () -> assertThat(result.getOrDefault("PUSH", List.of())).isEqualTo(expectedPush),
                () -> assertThat(result.get("LOSE")).isEqualTo(expectedLose)
        );
    }

    @DisplayName("버스트가 발생한 경우 플레이어의 승패 여부가 제대로 판별되는지 확인한다.")
    @Test
    public void burstWinTest() {
        List<Player> expectedWin = new ArrayList<>();
        List<Player> expectedLose = new ArrayList<>();

        Dealer dealer = new Dealer();
        List<Card> cardListD = new ArrayList<>();
        cardListD.add(new Card(Suit.SPADE, Rank.NINE));
        cardListD.add(new Card(Suit.SPADE, Rank.SIX));
        cardListD.add(new Card(Suit.DIAMOND, Rank.ACE));
        dealer.addCard(cardListD);

        Player player1 = new Player("배정환", 40000);
        List<Card> cardList1 = new ArrayList<>();
        cardList1.add(new Card(Suit.HEART, Rank.NINE));
        cardList1.add(new Card(Suit.SPADE, Rank.SEVEN));
        cardList1.add(new Card(Suit.CLOVER, Rank.ACE));
        player1.addCard(cardList1);

        Player player2 = new Player("apple", 20000);
        List<Card> cardList2 = new ArrayList<>();
        cardList2.add(new Card(Suit.SPADE, Rank.EIGHT));
        cardList2.add(new Card(Suit.SPADE, Rank.QUEEN));
        cardList2.add(new Card(Suit.HEART, Rank.KING));
        player2.addCard(cardList2);

        playerList.add(player1);
        playerList.add(player2);
        expectedWin.add(player1);
        expectedLose.add(player2);

        Map<String, List<Player>> result = blackJack.checkWinner(playerList, dealer);

        assertAll(
                () -> assertThat(result.get("WIN")).isEqualTo(expectedWin),
                () -> assertThat(result.get("LOSE")).isEqualTo(expectedLose)
        );
    }

    @DisplayName("블랙잭이 발생한 경우 플레이어의 승패 여부가 제대로 판별되는지 확인한다.")
    @Test
    public void blackJackWinTest() {
        List<Player> expectedWin = new ArrayList<>();
        List<Player> expectedLose = new ArrayList<>();

        Dealer dealer = new Dealer();
        List<Card> cardListD = new ArrayList<>();
        cardListD.add(new Card(Suit.SPADE, Rank.TEN));
        cardListD.add(new Card(Suit.SPADE, Rank.FOUR));
        cardListD.add(new Card(Suit.DIAMOND, Rank.SEVEN));
        dealer.addCard(cardListD);

        Player player1 = new Player("배정환", 40000);
        List<Card> cardList1 = new ArrayList<>();
        cardList1.add(new Card(Suit.HEART, Rank.QUEEN));
        cardList1.add(new Card(Suit.SPADE, Rank.ACE));
        player1.addCard(cardList1);

        Player player2 = new Player("apple", 20000);
        List<Card> cardList2 = new ArrayList<>();
        cardList2.add(new Card(Suit.CLOVER, Rank.SIX));
        cardList2.add(new Card(Suit.CLOVER, Rank.TWO));
        cardList2.add(new Card(Suit.HEART, Rank.THREE));
        player2.addCard(cardList2);

        playerList.add(player1);
        playerList.add(player2);
        expectedWin.add(player1);
        expectedLose.add(player2);

        Map<String, List<Player>> result = blackJack.checkWinner(playerList, dealer);

        assertAll(
                () -> assertThat(result.get("WIN")).isEqualTo(expectedWin),
                () -> assertThat(result.get("LOSE")).isEqualTo(expectedLose)
        );
    }

    @DisplayName("블랙잭이 발생한 경우 배팅 금액이 1.5배가 되는지 확인한다.")
    @Test
    public void checkBlackJack() {
        double expected = 60000;

        Player player = new Player("배정환", 40000);
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.SPADE, Rank.ACE));
        cardList.add(new Card(Suit.CLOVER, Rank.TEN));
        player.addCard(cardList);
        player.checkBlackJack();
        double result = player.getAmount();

        assertThat(expected).isEqualTo(result);
    }
}
