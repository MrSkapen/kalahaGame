import java.util.ArrayList;

import interfaces.KalahaState;
import interfaces.KalahaState.GameStates;

public class Player1Strategy implements Strategy {

    @Override
    public void afterPlayerTurn(ArrayList<Integer> currentBoard, ArrayList<Integer> boardOne, ArrayList<Integer> boardTwo, KalahaStateImpl kalahaState) {
        currentBoard.addAll(boardOne);
        currentBoard.addAll(boardTwo);
        kalahaState.setGameState(KalahaState.GameStates.AFTER_PLAYER1_TURN);
    }
}