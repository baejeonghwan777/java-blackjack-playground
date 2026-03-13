package nextstep;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Gamers {
    private final List<Gamer> gamerList = new ArrayList<>();
    private List<Player> playerList = new ArrayList<>();
    private final Dealer dealer = new Dealer();

    public void addDealer() {
        gamerList.add(dealer);
    }

    public void addPlayer(String name, int betAmount) {
        gamerList.add(new Player(name, betAmount));
    }

    public void setPlayer() {
        playerList = gamerList.stream().filter(p -> p instanceof Player).map(player -> (Player) player).toList();
    }

    public List<String> getPlayerName() {
        return playerList.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Gamer> getGamerList() {
        return gamerList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
