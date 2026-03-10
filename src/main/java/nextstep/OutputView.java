package nextstep;

import java.util.List;

public class OutputView {

    public static void printInitInfo(List<String> names) {
        String name = String.join(",");
        System.out.println("딜러와 " + name + "에게 2장을 나눕니다.");
    }

    public static void printMyCard(String name, List<String> cardList) { // 수정 필요
        System.out.println(name + ": " + String.join(",", cardList));
    }

    public static void printMySum(String name, List<String> cardList, int sumScore) { // 수정 필요
        System.out.println(name + ": " + String.join(",", cardList) + " - 결과 : " + sumScore);
    }

    public static void printMyResult(double money) {
        System.out.println();
    }

    public static void printDealerCard(List<String> cardList) {
        System.out.println("딜러: " + String.join(",", cardList));
    }

    public static void printDealerSum(List<String> cardList, int sumScore) {
        System.out.println("딜러: " + String.join(",", cardList) + " - 결과 : " + sumScore);
    }

    public static void printDealerResult(double money) {

    }

    public static void printDrawInfo(int drawCount) {
        System.out.println("딜러의 점수가 16 이하라 " + drawCount + "장의 카드를 더 받았습니다.");
    }
}
