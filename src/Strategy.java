import java.util.ArrayList;

import interfaces.KalahaState.GameStates;

public interface Strategy {
    void afterPlayerTurn(ArrayList<Integer> currentBoard, ArrayList<Integer> boardOne, ArrayList<Integer> boardTwo, GameStates gameState);
}
