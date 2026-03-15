package nextstep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BlackJackTest {
    Control control;
    Gamers gamers;
    CardDeck cards;
    Dealer dealer;
    List<Player> playerList;
    List<Gamer> gamerList;

    @BeforeEach
    public void setUp() {
        control = new Control();
        gamers = new Gamers();
        cards = new CardDeck();
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
                test.add(Card.of(suit, rank));
            }
        }

        CardDeck cards = new CardDeck();
        List<Card> cardList = cards.draw(test.size());
        Set<Card> result = new HashSet<>(cardList);

        assertThat(result).isEqualTo(test);
    }

    @DisplayName("드로우한 카드가 플레이어에게 제대로 반영이 되는지 확인한다.")
    @Test
    public void drawValidPlayerTest() {
        int expected = 2;

        Player player = new Player("배정환", 40000);
        control.drawCard(player, expected);
        List<String> cardString = player.getCardName();

        assertThat(cardString.size()).isEqualTo(expected);
    }

    @DisplayName("드로우한 카드가 딜러에게 제대로 반영이 되는지 확인한다.")
    @Test
    public void drawValidDealerTest() {
        int expected = 2;

        control.drawCard(dealer, expected);
        List<String> cardString = dealer.getCardName();

        assertThat(cardString.size()).isEqualTo(expected);
    }

    @DisplayName("딜러가 첫 턴에 두장의 카드를 뽑았을 때 한장만 카드만 출력되는지 확인한다.")
    @Test
    public void drawValidDealerPrintTest() {
        int expected = 1;

        control.drawCard(dealer, expected);
        List<String> cardString = dealer.getCardNameInit();

        assertThat(cardString.size()).isEqualTo(expected);
    }


    @DisplayName("딜러가 카드 두장을 뽑고 16보다 합계 숫자가 낮은 경우 카드를 뽑는지 확인한다.")
    @Test
    public void extraDrawTest() {
        boolean expected = true;

        control.controlDealer(dealer);
        boolean result = dealer.sumScore() >= 16;

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("에이스가 11점일 때 플레이어의 카드 점수 합계가 잘 반영되는지 확인한다.")
    @Test
    public void playerSumElevenTest() {
        int expected = 21;

        Player player = new Player("배정환", 40000);
        List<Card> cardList = new ArrayList<>();
        cardList.add(Card.of(Suit.SPADE, Rank.QUEEN));
        cardList.add(Card.of(Suit.DIAMOND, Rank.ACE));
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
        cardList.add(Card.of(Suit.SPADE, Rank.NINE));
        cardList.add(Card.of(Suit.SPADE, Rank.SIX));
        cardList.add(Card.of(Suit.DIAMOND, Rank.ACE));
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
        cardList.add(Card.of(Suit.SPADE, Rank.NINE));
        cardList.add(Card.of(Suit.SPADE, Rank.SIX));
        cardList.add(Card.of(Suit.DIAMOND, Rank.ACE));
        player.addCard(cardList);

        List<String> result = player.getCardName();
        expected.add(cardList.get(0).toString());
        expected.add(cardList.get(1).toString());
        expected.add(cardList.get(2).toString());

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("딜러 점수와 비교하여 플레이어의 승패 여부가 제대로 판별되는지 확인한다.")
    @Test
    public void Test() {
        double expected1 = 40000;
        double expected2 = 0;
        double expected3 = -30000;
        double expectedD = -10000;

        Dealer dealer = new Dealer();
        List<Card> cardListD = new ArrayList<>();
        cardListD.add(Card.of(Suit.SPADE, Rank.NINE));
        cardListD.add(Card.of(Suit.SPADE, Rank.SIX));
        dealer.addCard(cardListD);
        cardListD.clear();
        cardListD.add(Card.of(Suit.DIAMOND, Rank.ACE));
        dealer.addCard(cardListD);

        Player player1 = new Player("배정환", 40000);
        List<Card> cardList1 = new ArrayList<>();
        cardList1.add(Card.of(Suit.HEART, Rank.NINE));
        cardList1.add(Card.of(Suit.SPADE, Rank.SEVEN));
        player1.addCard(cardList1); // 2장 뽑아오는 것 구현 뽑아옴으로써 상태가 started -> hit으로 변경됨
        cardList1.clear();
        cardList1.add(Card.of(Suit.CLOVER, Rank.ACE));
        player1.addCard(cardList1);
        player1.setStay();

        Player player2 = new Player("apple", 20000);
        List<Card> cardList2 = new ArrayList<>();
        cardList2.add(Card.of(Suit.SPADE, Rank.EIGHT));
        cardList2.add(Card.of(Suit.SPADE, Rank.THREE));
        player2.addCard(cardList2);
        cardList2.clear();
        cardList2.add(Card.of(Suit.HEART, Rank.FIVE));
        player2.addCard(cardList2);
        player2.setStay();

        Player player3 = new Player("banana", 30000);
        List<Card> cardList3 = new ArrayList<>();
        cardList3.add(Card.of(Suit.DIAMOND, Rank.TWO));
        cardList3.add(Card.of(Suit.CLOVER, Rank.SIX));
        player3.addCard(cardList3);
        cardList3.clear();
        cardList3.add(Card.of(Suit.DIAMOND, Rank.FIVE));
        player3.addCard(cardList3);
        player3.setStay();

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);

        double resultD = control.result(playerList, dealer);

        assertAll(
                () -> assertThat(expected1).isEqualTo(player1.getProfit(dealer)),
                () -> assertThat(expected2).isEqualTo(player2.getProfit(dealer)),
                () -> assertThat(expected3).isEqualTo(player3.getProfit(dealer)),
                () -> assertThat(expectedD).isEqualTo(resultD)
        );
    }

    @DisplayName("버스트가 발생한 경우 플레이어의 승패 여부가 제대로 판별되는지 확인한다.")
    @Test
    public void burstWinTest() {
        double expected1 = 40000;
        double expected2 = -20000;
        double expectedD = -20000;

        Dealer dealer = new Dealer();
        List<Card> cardListD = new ArrayList<>();
        cardListD.add(Card.of(Suit.SPADE, Rank.NINE));
        cardListD.add(Card.of(Suit.SPADE, Rank.SIX));
        dealer.addCard(cardListD);
        cardListD.clear();
        cardListD.add(Card.of(Suit.DIAMOND, Rank.ACE));
        dealer.addCard(cardListD);

        Player player1 = new Player("배정환", 40000);
        List<Card> cardList1 = new ArrayList<>();
        cardList1.add(Card.of(Suit.HEART, Rank.NINE));
        cardList1.add(Card.of(Suit.SPADE, Rank.SEVEN));
        player1.addCard(cardList1);
        cardList1.clear();
        cardList1.add(Card.of(Suit.CLOVER, Rank.ACE));
        player1.addCard(cardList1);
        player1.setStay();

        Player player2 = new Player("apple", 20000);
        List<Card> cardList2 = new ArrayList<>();
        cardList2.add(Card.of(Suit.SPADE, Rank.EIGHT));
        cardList2.add(Card.of(Suit.SPADE, Rank.QUEEN));
        player2.addCard(cardList2);
        cardList2.clear();
        cardList2.add(Card.of(Suit.HEART, Rank.KING));
        player2.addCard(cardList2);

        playerList.add(player1);
        playerList.add(player2);

        double resultD = control.result(playerList, dealer);

        assertAll(
                () -> assertThat(expected1).isEqualTo(player1.getProfit(dealer)),
                () -> assertThat(expected2).isEqualTo(player2.getProfit(dealer)),
                () -> assertThat(expectedD).isEqualTo(resultD)
        );
    }

    @DisplayName("모든 플레이어가 버스트가 발생한 경우 플레이어의 승패 여부가 제대로 판별되는지 확인한다.")
    @Test
    public void burstLoseTest() {
        double expected1 = -40000;
        double expected2 = -20000;
        double expectedD = 60000;

        Dealer dealer = new Dealer();
        List<Card> cardListD = new ArrayList<>();
        cardListD.add(Card.of(Suit.SPADE, Rank.NINE));
        cardListD.add(Card.of(Suit.SPADE, Rank.TEN));
        dealer.addCard(cardListD);
        cardListD.clear();
        cardListD.add(Card.of(Suit.DIAMOND, Rank.QUEEN));
        dealer.addCard(cardListD);

        Player player1 = new Player("배정환", 40000);
        List<Card> cardList1 = new ArrayList<>();
        cardList1.add(Card.of(Suit.HEART, Rank.NINE));
        cardList1.add(Card.of(Suit.SPADE, Rank.JACK));
        player1.addCard(cardList1);
        cardList1.clear();
        cardList1.add(Card.of(Suit.CLOVER, Rank.TEN));
        player1.addCard(cardList1);

        Player player2 = new Player("apple", 20000);
        List<Card> cardList2 = new ArrayList<>();
        cardList2.add(Card.of(Suit.SPADE, Rank.EIGHT));
        cardList2.add(Card.of(Suit.SPADE, Rank.QUEEN));
        player2.addCard(cardList2);
        cardList2.clear();
        cardList2.add(Card.of(Suit.HEART, Rank.KING));
        player2.addCard(cardList2);

        playerList.add(player1);
        playerList.add(player2);

        double resultD = control.result(playerList, dealer);

        assertAll(
                () -> assertThat(expected1).isEqualTo(player1.getProfit(dealer)),
                () -> assertThat(expected2).isEqualTo(player2.getProfit(dealer)),
                () -> assertThat(expectedD).isEqualTo(resultD)
        );
    }

    @DisplayName("블랙잭이 발생한 경우 플레이어의 승패 여부가 제대로 판별되는지 확인한다.")
    @Test
    public void blackJackWinTest() {
        double expected1 = 60000;
        double expected2 = -20000;
        double expectedD = -40000;

        Dealer dealer = new Dealer();
        List<Card> cardListD = new ArrayList<>();
        cardListD.add(Card.of(Suit.SPADE, Rank.TEN));
        cardListD.add(Card.of(Suit.SPADE, Rank.FOUR));
        dealer.addCard(cardListD);
        cardListD.clear();
        cardListD.add(Card.of(Suit.DIAMOND, Rank.SEVEN));
        dealer.addCard(cardListD);

        Player player1 = new Player("배정환", 40000);
        List<Card> cardList1 = new ArrayList<>();
        cardList1.add(Card.of(Suit.HEART, Rank.QUEEN));
        cardList1.add(Card.of(Suit.SPADE, Rank.ACE));
        player1.addCard(cardList1);

        Player player2 = new Player("apple", 20000);
        List<Card> cardList2 = new ArrayList<>();
        cardList2.add(Card.of(Suit.CLOVER, Rank.SIX));
        cardList2.add(Card.of(Suit.CLOVER, Rank.TWO));
        player2.addCard(cardList2);
        cardList2.clear();
        cardList2.add(Card.of(Suit.HEART, Rank.THREE));
        player2.addCard(cardList2);
        player2.setStay();

        playerList.add(player1);
        playerList.add(player2);

        double resultD = control.result(playerList, dealer);

        assertAll(
                () -> assertThat(expected1).isEqualTo(player1.getProfit(dealer)),
                () -> assertThat(expected2).isEqualTo(player2.getProfit(dealer)),
                () -> assertThat(expectedD).isEqualTo(resultD)
        );
    }
}
