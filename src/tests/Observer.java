package tests;

import interfaces.GameStateObserver;
import interfaces.KalahaState;

public class Observer implements GameStateObserver {
    @Override
    public void currentState(KalahaState state) {
        System.out.println("Pits state: " + state.getPitsState());
        System.out.println("Game state: " + state.getGameState().name());
        System.out.println("Game result: " + state.getGameResult());
    }
}
