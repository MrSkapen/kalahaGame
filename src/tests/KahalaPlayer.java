package tests;

import interfaces.KalahPlayer;

import java.util.List;
import java.util.Scanner;

public class KahalaPlayer implements KalahPlayer {

    private final String playerId;

    public KahalaPlayer(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public int yourMove(List<Integer> pitsState) {
        System.out.println("Ruch gracza: " + playerId);
        Scanner scanner = new Scanner(System.in);
        String move = scanner.nextLine();
        return Integer.parseInt(move);
    }
}
