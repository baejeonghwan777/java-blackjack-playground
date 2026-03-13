package nextstep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class InputView {
    static Scanner scanner = new Scanner(System.in);

    public static List<String> inputName() {
        String names;
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
            names = scanner.nextLine();
        } while (validName(names).equals(new ArrayList<String>()));
        return validName(names);
    }

    public static int inputMoney(String name) {
        int money = 0;
        do {
            System.out.println(name + "의 배팅 금액을 입력하세요.");
            try {
                money = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("올바른 숫자를 입력하세요.");
                scanner.nextLine();
            }
        } while (money < 0);
        return money;
    }

    private static List<String> validName(String names) {
        List<String> name;
        try {
            name = Arrays.asList(names.split(","));
        } catch (PatternSyntaxException e) {
            return new ArrayList<>();
        }
        return name;
    }

    public static boolean selectAddCard(String name) {
        String result;
        do {
            System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            result = scanner.nextLine();
            if(result.equals("y")) return true;
            if(result.equals("n")) return false;
            System.out.println("올바른 값을 입력해주세요.");
        } while(true);
    }
}
