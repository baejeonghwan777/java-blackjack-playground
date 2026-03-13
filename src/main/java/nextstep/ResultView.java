package nextstep;

import java.util.List;

public class ResultView {

    public static void printInitInfo(List<String> names) {
        String name = String.join(", ", names);
        System.out.println("딜러와 " + name + "에게 2장을 나눕니다.");
    }

    public static void printCard(String name, List<String> cardList) {
        System.out.println(name + " 카드: " + String.join(", ", cardList));
    }

    public static void printSum(String name, List<String> cardList, int sumScore) {
        System.out.println(name + " 카드: " + String.join(", ", cardList) + " - 결과 : " + sumScore);
    }

    public static void printResult(String name, double money) {
        System.out.println(name + " 수익: " + (int) money);
    }

    public static void printDrawInfo(int drawCount) {
        System.out.println("딜러의 점수가 16 이하라 " + drawCount + "장의 카드를 더 받았습니다.");
    }
}
