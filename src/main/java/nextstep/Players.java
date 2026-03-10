package nextstep;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> playerList = new ArrayList<>();

    public void addPlayer(List<String> name, int money) {
        for (String string : name) {
            playerList.add(new Player(string, money));
        }
    }

    public List<String> getPlayerName() {
        List<String> names = new ArrayList<>();
        for (Player player : playerList) {
            names.add(player.getName());
        }
        return names;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
